var seckill = {
    URL: {
        now: function () {
            return contextPath + 'seckill/time/now';
        },
        exposer: function (seckillId) {
            return contextPath + 'seckill/' + seckillId + '/exposer';
        },
        execution: function (seckillId, md5) {
            return contextPath + 'seckill/' + seckillId + '/' + md5 + '/execution';
        }
    },
    handleSeckill: function (seckillId, node) {
        node.hide().html('<button class="btn btn-primary btn-lg" id="killBtn">开始秒杀</button>');
        $.post(seckill.URL.exposer(seckillId), {}, function (exposer) {
            if (exposer && exposer['code'] == '0') {
                exposer = exposer['data'];
                if (exposer['exposed']) {
                    var md5 = exposer['md5'];
                    var killUrl = seckill.URL.execution(seckillId, md5);
                    $('#killBtn').one('click', function () {
                        $(this).addClass('disable');
                        $.post(killUrl, {}, function (result) {
                            if (result) {
                                var code = result['code'];
                                var message = result['msg'];
                                if (code == 0) {
                                    node.html('<span class="label label-danger">秒杀成功!</span>');
                                } else {
                                    node.html('<span class="label label-success">' + message + '</span>');
                                }
                            }
                        });
                    });
                    node.show();
                } else {
                    var currentTime = exposer['currentTime'];
                    var startTime = exposer['startTime'];
                    var endTime = exposer['endTime'];
                    seckill.countdown(seckillId, currentTime, startTime, endTime);
                }
            }
        });
    },
    valdateMobile: function (mobile) {
        if (mobile && mobile.length == 11 && !isNaN(mobile)) {
            return true;
        } else {
            return false;
        }
    },
    countdown: function (seckillId, nowTime, startTime, endTime) {
        var seckillBox = $('#seckill-box');
        if (nowTime > endTime) {
            seckillBox.html('秒杀结束');
        } else if (nowTime < startTime) {
            var killTime = new Date(startTime - 0 + 500);
            seckillBox.countdown(killTime, function (event) {
                var format = event.strftime('秒杀倒计时：%D天 %H时 %M分 %S秒');
                seckillBox.html(format);
            }).on('finish.countdown', function () {
                seckill.handleSeckill(seckillId, seckillBox);
            });
        } else {
            seckill.handleSeckill(seckillId, seckillBox);
        }
    },
    detail: {
        init: function (params) {
            var userMobile = $.cookie('userMobile');
            if (!seckill.valdateMobile(userMobile)) {
                var killMobileModal = $('#killMobileModal');
                killMobileModal.modal({
                    show: true,
                    backdrop: 'static',
                    keyboard: false
                });
                $('#userMobileBtn').click(function () {
                    var inputMoblie = $('#userMobileKey').val();
                    if (seckill.valdateMobile(inputMoblie)) {
                        $.cookie('userMobile', inputMoblie, {
                            expires: 7,
                            path: contextPath + 'seckill'
                        });
                        window.location.reload();
                    } else {
                        $('#userMobileMessage').hide().html('<label class="label label-danger">手机号错误</label>').show(300);
                    }
                });
            }
            var startTime = params['startTime'];
            var endTime = params['endTime'];
            var seckillId = params['seckillId'];
            $.get(seckill.URL.now(), {}, function (result) {
                if (result) {
                    var nowTime = result;
                    seckill.countdown(seckillId, nowTime, startTime, endTime);
                } else {
                    console.log(result);
                }
            });
        }
    }
}
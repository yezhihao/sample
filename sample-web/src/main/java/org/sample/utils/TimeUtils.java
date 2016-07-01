package org.sample.utils;

import java.util.List;

import freemarker.template.SimpleDate;
import freemarker.template.TemplateMethodModelEx;
import freemarker.template.TemplateModelException;

public class TimeUtils implements TemplateMethodModelEx {

    @SuppressWarnings("rawtypes")
    @Override
    public Object exec(List arguments) throws TemplateModelException {
        SimpleDate date = (SimpleDate) arguments.get(0);
        return String.valueOf(date.getAsDate().getTime());
    }
}
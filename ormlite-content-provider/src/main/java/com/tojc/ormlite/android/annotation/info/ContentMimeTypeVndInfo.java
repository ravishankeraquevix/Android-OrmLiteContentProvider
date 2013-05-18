package com.tojc.ormlite.android.annotation.info;

import java.lang.reflect.AnnotatedElement;

import org.apache.commons.lang3.StringUtils;

import com.tojc.ormlite.android.annotation.AdditionalAnnotation.DefaultContentMimeTypeVnd;

/**
 * Manage the MIME Types information.
 * @author Jaken
 */
public class ContentMimeTypeVndInfo extends AnnotationInfoBase {
    // ----------------------------------
    // CONSTANTS
    // ----------------------------------
    public static final String VND = "vnd";
    public static final String PROVIDER_SUFFIX = ".provider";

    // ----------------------------------
    // ATRRIBUTES
    // ----------------------------------
    private String name;
    private String type;

    // ----------------------------------
    // CONSTRUCTORS
    // ----------------------------------

    public ContentMimeTypeVndInfo(AnnotatedElement element) {
        DefaultContentMimeTypeVnd contentMimeTypeVnd = element.getAnnotation(DefaultContentMimeTypeVnd.class);
        String name = null;
        String type = null;
        if (contentMimeTypeVnd != null) {
            name = contentMimeTypeVnd.name();
            type = contentMimeTypeVnd.type();

            if (element instanceof Class<?>) {
                Class<?> clazz = (Class<?>) element;
                if (StringUtils.isEmpty(name)) {
                    name = clazz.getPackage().getName() + PROVIDER_SUFFIX;
                }

                if (StringUtils.isEmpty(type)) {
                    type = clazz.getSimpleName().toLowerCase();
                }
            }
        }

        initialize(name, type);
    }

    public ContentMimeTypeVndInfo(String name, String type) {
        initialize(name, type);
    }

    // ----------------------------------
    // PUBLIC METHODS
    // ----------------------------------
    public String getName() {
        return this.name;
    }

    public String getType() {
        return this.type;
    }

    public String getVndProviderSpecificString() {
        return VND + "." + this.name + "." + this.type;
    }

    @Override
    protected boolean isValidValue() {
        return StringUtils.isNotEmpty(this.name) && StringUtils.isNotEmpty(this.type);
    }

    // ----------------------------------
    // PRIVATE METHODS
    // ----------------------------------
    private void initialize(String name, String type) {
        this.name = name;
        this.type = type;
        validFlagOn();
    }

}

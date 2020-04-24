package com.example.videorecordanduploadexample.widget;

import android.content.Context;
import android.graphics.BitmapFactory;

import com.example.videorecordanduploadexample.R;
import com.example.videorecordanduploadexample.camerarecorder.egl.filter.GlBilateralFilter;
import com.example.videorecordanduploadexample.camerarecorder.egl.filter.GlBoxBlurFilter;
import com.example.videorecordanduploadexample.camerarecorder.egl.filter.GlBulgeDistortionFilter;
import com.example.videorecordanduploadexample.camerarecorder.egl.filter.GlCGAColorspaceFilter;
import com.example.videorecordanduploadexample.camerarecorder.egl.filter.GlFilter;
import com.example.videorecordanduploadexample.camerarecorder.egl.filter.GlFilterGroup;
import com.example.videorecordanduploadexample.camerarecorder.egl.filter.GlGaussianBlurFilter;
import com.example.videorecordanduploadexample.camerarecorder.egl.filter.GlGrayScaleFilter;
import com.example.videorecordanduploadexample.camerarecorder.egl.filter.GlInvertFilter;
import com.example.videorecordanduploadexample.camerarecorder.egl.filter.GlLookUpTableFilter;
import com.example.videorecordanduploadexample.camerarecorder.egl.filter.GlMonochromeFilter;
import com.example.videorecordanduploadexample.camerarecorder.egl.filter.GlSepiaFilter;
import com.example.videorecordanduploadexample.camerarecorder.egl.filter.GlSharpenFilter;
import com.example.videorecordanduploadexample.camerarecorder.egl.filter.GlSphereRefractionFilter;
import com.example.videorecordanduploadexample.camerarecorder.egl.filter.GlToneCurveFilter;
import com.example.videorecordanduploadexample.camerarecorder.egl.filter.GlToneFilter;
import com.example.videorecordanduploadexample.camerarecorder.egl.filter.GlVignetteFilter;
import com.example.videorecordanduploadexample.camerarecorder.egl.filter.GlWeakPixelInclusionFilter;

import java.io.InputStream;

public enum Filters {
    NORMAL,
    BILATERAL,
    BOX_BLUR,
    BULGE_DISTORTION,
    CGA_COLOR_SPACE,
    GAUSSIAN_BLUR,
    GLAY_SCALE,
    INVERT,
    LOOKUP_TABLE,
    MONOCHROME,
    OVERLAY,
    SEPIA,
    SHARPEN,
    SPHERE_REFRACTION,
    TONE_CURVE,
    TONE,
    VIGNETTE,
    WEAKPIXELINCLUSION,
    FILTER_GROUP;

    public static GlFilter getFilterInstance(Filters filter, Context context) {
        switch (filter) {
            case BILATERAL:
                return new GlBilateralFilter();
            case BOX_BLUR:
                return new GlBoxBlurFilter();
            case BULGE_DISTORTION:
                return new GlBulgeDistortionFilter();
            case CGA_COLOR_SPACE:
                return new GlCGAColorspaceFilter();
            case GAUSSIAN_BLUR:
                return new GlGaussianBlurFilter();
            case GLAY_SCALE:
                return new GlGrayScaleFilter();
            case INVERT:
                return new GlInvertFilter();
            case LOOKUP_TABLE:
                return new GlLookUpTableFilter(BitmapFactory.decodeResource(context.getResources(), R.drawable.lookup_sample));
            case MONOCHROME:
                return new GlMonochromeFilter();
            case OVERLAY:
                return new GlBitmapOverlaySample(BitmapFactory.decodeResource(context.getResources(), R.mipmap.ic_launcher_round));
            case SEPIA:
                return new GlSepiaFilter();
            case SHARPEN:
                return new GlSharpenFilter();
            case SPHERE_REFRACTION:
                return new GlSphereRefractionFilter();
            case TONE_CURVE:
                try {
                    InputStream inputStream = context.getAssets().open("acv/tone_cuver_sample.acv");
                    return new GlToneCurveFilter(inputStream);
                } catch (Exception e) {
                    return new GlFilter();
                }
            case TONE:
                return new GlToneFilter();
            case VIGNETTE:
                return new GlVignetteFilter();
            case WEAKPIXELINCLUSION:
                return new GlWeakPixelInclusionFilter();
            case FILTER_GROUP:
                return new GlFilterGroup(new GlMonochromeFilter(), new GlVignetteFilter());

            default:
                return new GlFilter();
        }

    }

}

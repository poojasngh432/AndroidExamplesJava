package com.example.videorecordanduploadexample.recorder.listener;

public interface RecordStateListener {

    void recordStart();

    void recordEnd(long time);

    void recordCancel();
}

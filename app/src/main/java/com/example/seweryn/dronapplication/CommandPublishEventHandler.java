package com.example.seweryn.dronapplication;

/**
 * Created by Mateusz on 18.08.2017.
 */

public interface CommandPublishEventHandler
{
    /** */
    void sendNotepadCommand();

    /** */
    void sendRebootCommand();

    /** */
    void reconnect();
}

package com.example.seweryn.dronapplication;

import android.support.v7.app.AppCompatDelegate;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

/**
 * Created by Mateusz on 18.08.2017.
 */

public class CommandController
{
    /** */
    private final AppCompatDelegate delegate;

    /** */
    private final CommandPublishEventHandler commandPublishEventHandler;

    /** */
    public CommandController(final AppCompatDelegate delegate, final CommandPublishEventHandler commandPublishEventHandler)
    {
        this.delegate = delegate;
        this.commandPublishEventHandler = commandPublishEventHandler;
    }

    /** */
    public void setUpControls()
    {
       /* final Button notepadButton = (Button)delegate.findViewById(R.id.notepadButton);
        setUpNotepadButton(notepadButton);

        final Button rebootButton = (Button)delegate.findViewById(R.id.rebootButton);
        setUpRebootButton(rebootButton);

        final Button shutdownButton = (Button)delegate.findViewById(R.id.shutdownButton);
        setUpShutdownButton(shutdownButton);*/

        final Button reconnectButton = (Button)delegate.findViewById(R.id.buttonReconnect);
        setUpReconnectButton(reconnectButton);
    }

    /** *//*
    private void setUpNotepadButton(final Button button)
    {
        button.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(final View v)
            {
                commandPublishEventHandler.sendNotepadCommand();
            }
        });
    }

    *//** *//*
    private void setUpRebootButton(final Button button)
    {
        button.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(final View v)
            {
                commandPublishEventHandler.sendRebootCommand();
            }
        });
    }

    *//** *//*
    private void setUpShutdownButton(final Button button)
    {
        button.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(final View v)
            {
                commandPublishEventHandler.sendShutdownCommand();
            }
        });
    }*/

    /** */
    private void setUpReconnectButton(final Button button)
    {
        button.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(final View v)
            {
                commandPublishEventHandler.reconnect();
            }
        });
    }

    /** */
    public TextView getDebugLabel()
    {
        return (TextView)delegate.findViewById(R.id.debugLabel);
    }
}

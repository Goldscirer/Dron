package com.example.seweryn.dronapplication;

import android.support.v7.app.AppCompatDelegate;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.util.Log;

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
        final Button reconnectButton = (Button)delegate.findViewById(R.id.buttonReconnect);
        setUpReconnectButton(reconnectButton);
    }

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
        Log.e("test", "dupa");
        return (TextView)delegate.findViewById(R.id.debugLabel);
    }
}

// Alex Diker
// 100746284
// Mobile Development

package comp3074.assignment2;

import android.content.Intent;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.support.v4.app.FragmentActivity;
import android.widget.TextView;

public class AccelerometerEventListener implements SensorEventListener
{

// Display the string for the Accelerometer which is default on the application: (should always be displayed)

    private final String d_text = "Accelerometer: "; // The default text to display before the accelerometer data.

// Lets display the data using this text view.

    private float m_lastA, m_lastB, m_lastC;
    private long m_update = 0;
    private TextView m_textView;
    private FragmentActivity m_act;

// how much change has to happen for the accelerometer starts working.

    private final float m_accelCompare = 1.0f;

    private final float m_shakeThres = 800.0f;

// set AccelerometerEventLister as requested in the application specifications

    public AccelerometerEventListener (TextView textView, FragmentActivity activity)
    {
        m_act = activity;
        m_textView = textView;
    }

// When Sensor Gets Changed

    public void onSensorChanged (SensorEvent event)
    {
        if (event.sensor.getType() == Sensor.TYPE_ACCELEROMETER)
        {
            long currTime = System.currentTimeMillis();
            if (currTime - m_update < 100)
                return;

            long updated_time = (currTime - m_update);
            m_update = currTime;

            float a = event.values[0];
            float b = event.values[1];
            float c = event.values[2];

// Does math for speedMovement

            float speedMovement = Math.abs(a + b + c - m_lastB - m_lastA - m_lastC) / updated_time * 10000;

            if (speedMovement > m_shakeThres)
            {
                Intent intent = new Intent(m_act, AlertActivity.class);
                m_act.startActivity(intent);
            }

// Does calculations for Sqrt

            float Sqrt = (a*a + b*b + c*c) / (SensorManager.GRAVITY_EARTH * SensorManager.GRAVITY_EARTH);

            if (Sqrt >= m_accelCompare)
            {
                m_textView.setText(d_text + Sqrt);
            }

            m_lastA = a;
            m_lastB = b;
            m_lastC = c;
        }
    }

    public void onAccuracyChanged (Sensor sensor, int accuracy)
    {

    }
}

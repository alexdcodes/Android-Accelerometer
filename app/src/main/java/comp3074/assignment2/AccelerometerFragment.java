// Alex Diker
// 100746284
// Mobile Application Development

package comp3074.assignment2;

import android.content.Context;
import android.hardware.Sensor;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

public class AccelerometerFragment extends Fragment
{
    private SensorManager m_sensorMan;

    private AccelerometerEventListener m_Listener;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState)
    {
        View view = inflater.inflate(R.layout.frag_accelerometer, container, false);

        m_sensorMan = (SensorManager)this.getActivity().getSystemService(Context.SENSOR_SERVICE);

        TextView textView = (TextView)view.findViewById(R.id.frag_accelerometer);

        m_Listener = new AccelerometerEventListener(textView, getActivity());

        m_sensorMan.registerListener(m_Listener, m_sensorMan.getDefaultSensor(Sensor.TYPE_ACCELEROMETER), SensorManager.SENSOR_DELAY_NORMAL);

        return view;
    }

    @Override
    public void onPause()
    {
        super.onPause();

        m_sensorMan.unregisterListener(m_Listener);
    }

    @Override
    public void onResume()
    {
        super.onResume();

        m_sensorMan.registerListener(m_Listener, m_sensorMan.getDefaultSensor(Sensor.TYPE_ACCELEROMETER), SensorManager.SENSOR_DELAY_NORMAL);
    }
}

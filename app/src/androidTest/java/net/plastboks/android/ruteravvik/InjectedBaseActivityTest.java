package net.plastboks.android.ruteravvik;

import android.support.v7.app.AppCompatActivity;
import android.test.ActivityInstrumentationTestCase2;

import net.plastboks.android.ruteravvik.activity.BaseActivity;

public class InjectedBaseActivityTest<T extends AppCompatActivity> extends ActivityInstrumentationTestCase2<T>
{
    public InjectedBaseActivityTest(Class<T> activityClass)
    {
        super(activityClass);
    }

    @Override
    protected void setUp() throws Exception
    {
        super.setUp();

        App app = (App)getInstrumentation().getTargetContext().getApplicationContext();

    }

    @Override
    protected void tearDown() throws Exception
    {
        super.tearDown();
    }
}

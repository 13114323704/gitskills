package qqlog.qq;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

public class Splashactivity extends Activity{
	private Intent intent;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.splash);
		startMainAvtivity();
	}
	
	private void startMainAvtivity(){
	new Handler().postDelayed(new Runnable() {
		
		@Override
		public void run() {
			// TODO Auto-generated method stub
			intent=new Intent(Splashactivity.this,QQ.class);
			startActivity(intent);
			Splashactivity.this.finish();
		}
	}, 2000);
	}
	
}

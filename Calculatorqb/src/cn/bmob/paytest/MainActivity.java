package cn.bmob.paytest;

import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import cn.gqb.calculator.R;
import android.app.Activity;
import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioGroup;
import android.widget.RadioGroup.OnCheckedChangeListener;
import android.widget.TextView;
import android.widget.Toast;
import c.b.BP;
import c.b.PListener;
import c.b.QListener;

public class MainActivity extends Activity implements OnCheckedChangeListener {

	// 此为测试Appid,请将Appid改成你自己的Bmob AppId
	//String APPID = "9ae5cba4fbdc81a4a2832192bd1cb952";
	String APPID ="880c9a6549fc23118ce61e962aca4e90";
	EditText name, price, body, order;
	Button go;
	RadioGroup type;
	TextView tv;

	ProgressDialog dialog;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.bmob_main);

		// 必须先初始化
		BP.init(this, APPID);

		// 初始化BmobPay对象,可以在支付时再初始化
		name = (EditText) findViewById(R.id.name);
		price = (EditText) findViewById(R.id.price);
		body = (EditText) findViewById(R.id.body);
		order = (EditText) findViewById(R.id.order);
		go = (Button) findViewById(R.id.go);
		type = (RadioGroup) findViewById(R.id.type);
		tv = (TextView) findViewById(R.id.tv);

		type.setOnCheckedChangeListener(this);
		go.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// 当选择的是支付宝支付时
				if (type.getCheckedRadioButtonId() == R.id.alipay) {
					payByAli();
				} else if (type.getCheckedRadioButtonId() == R.id.wxpay) {
					// 调用插件用微信支付
					payByWeiXin();
				} else if (type.getCheckedRadioButtonId() == R.id.query) {
					// 选择查询时
					query();
				}
			}
		});

	}

	// 调用支付宝支付
	void payByAli() {
		showDialog("姝ｅ湪鑾峰彇璁㈠崟...");
		final String name = getName();

		tv.append("姝ｅ湪鑾峰彇璁㈠崟...");
		// BmobPay.init(this, APPID);

		BP.pay(this, name, getBody(), getPrice(), true, new PListener() {

			// 因为网络等原因,支付结果未知(小概率事件),出于保险起见稍后手动查询
			@Override
			public void unknow() {
				Toast.makeText(MainActivity.this, "支付结果未知,请稍后手动查询",
						Toast.LENGTH_SHORT).show();
				tv.append(name + "'s pay status is unknow\n\n");
				hideDialog();
			}

			// 支付成功,如果金额较大请手动查询确认
			@Override
			public void succeed() {
				Toast.makeText(MainActivity.this, "鏀粯鎴愬姛!", Toast.LENGTH_SHORT)
						.show();
				tv.append(name + "'s pay status is success\n\n");
				hideDialog();
			}

			// 无论成功与否,返回订单号
			@Override
			public void orderId(String orderId) {
				// 此处应该保存订单号,比如保存进数据库等,以便以后查询
				order.setText(orderId);
				tv.append(name + "'s orderid is " + orderId + "\n\n");
				showDialog("鑾峰彇璁㈠崟鎴愬姛!璇风瓑寰呰烦杞埌鏀粯椤甸潰~");
			}

			// 支付失败,原因可能是用户中断支付操作,也可能是网络原因
			@Override
			public void fail(int code, String reason) {
				Toast.makeText(MainActivity.this, "支付中断!", Toast.LENGTH_SHORT)
				.show();
				tv.append(name + "'s pay status is fail, error code is " + code
				+ " ,reason is " + reason + "\n\n");
				hideDialog();
			}
		});
	}

	// 调用微信支付
	void payByWeiXin() {
		showDialog("姝ｅ湪鑾峰彇璁㈠崟...");
		final String name = getName();

		BP.pay(this, name, getBody(), getPrice(), false, new PListener() {

			// 因为网络等原因,支付结果未知(小概率事件),出于保险起见稍后手动查询
			@Override
			public void unknow() {
				Toast.makeText(MainActivity.this, "支付结果未知,请稍后手动查询",
						Toast.LENGTH_SHORT).show();
				tv.append(name + "'s pay status is unknow\n\n");
				hideDialog();
			}

			// 支付成功,如果金额较大请手动查询确认
			@Override
			public void succeed() {
				Toast.makeText(MainActivity.this, "鏀粯鎴愬姛!", Toast.LENGTH_SHORT)
						.show();
				tv.append(name + "'s pay status is success\n\n");
				hideDialog();
			}

			// 无论成功与否,返回订单号
			@Override
			public void orderId(String orderId) {
				// 此处应该保存订单号,比如保存进数据库等,以便以后查询
				order.setText(orderId);
				tv.append(name + "'s orderid is " + orderId + "\n\n");
				showDialog("鑾峰彇璁㈠崟鎴愬姛!璇风瓑寰呰烦杞埌鏀粯椤甸潰~");
			}

			// 支付失败,原因可能是用户中断支付操作,也可能是网络原因
			@Override
			public void fail(int code, String reason) {

				// 当code为-2,意味着用户中断了操作
				// code为-3意味着没有安装BmobPlugin插件
				if (code == -3) {
					new AlertDialog.Builder(MainActivity.this)
							.setMessage(
									"监测到你尚未安装支付插件,无法进行微信支付,请选择安装插件(已打包在本地,无流量消耗)还是用支付宝支付")
							.setPositiveButton("安装",
									new DialogInterface.OnClickListener() {

										@Override
										public void onClick(
												DialogInterface dialog,
												int which) {
											installBmobPayPlugin("bp_wx.db");
										}
									})
							.setNegativeButton("支付宝支付",
									new DialogInterface.OnClickListener() {

										@Override
										public void onClick(
												DialogInterface dialog,
												int which) {
											payByAli();
										}
									}).create().show();
				} else {
					Toast.makeText(MainActivity.this, "支付中断!",
							Toast.LENGTH_SHORT).show();
				}
				tv.append(name + "'s pay status is fail, error code is " + code
						+ " ,reason is " + reason + "\n\n");
				hideDialog();
			}
		});
	}

	// 执行订单查询
	void query() {
		showDialog("正在查询订单...");
		final String orderId = getOrder();

		BP.query(this, orderId, new QListener() {

			@Override
			public void succeed(String status) {
				Toast.makeText(MainActivity.this, "查询成功!该订单状态为 : " + status,
						Toast.LENGTH_SHORT).show();
				tv.append("pay status of" + orderId + " is " + status + "\n\n");
				hideDialog();
			}

			@Override
			public void fail(int code, String reason) {
				Toast.makeText(MainActivity.this, "查询失败", Toast.LENGTH_SHORT)
						.show();
				tv.append("query order fail, error code is " + code
						+ " ,reason is " + reason + "\n\n");
				hideDialog();
			}
		});
	}

	// 以下仅为控件操作，可以略过
		@Override
		public void onCheckedChanged(RadioGroup group, int checkedId) {
			switch (checkedId) {
			case R.id.alipay:
				// 以下仅为控件操作，可以略过
				name.setVisibility(View.VISIBLE);
				price.setVisibility(View.VISIBLE);
				body.setVisibility(View.VISIBLE);
				order.setVisibility(View.GONE);
				go.setText("支付宝支付");
				break;
			case R.id.wxpay:
				// 以下仅为控件操作，可以略过
				name.setVisibility(View.VISIBLE);
				price.setVisibility(View.VISIBLE);
				body.setVisibility(View.VISIBLE);
				order.setVisibility(View.GONE);
				go.setText("微信支付");
				break;
			case R.id.query:
				// 以下仅为控件操作，可以略过
				name.setVisibility(View.GONE);
				price.setVisibility(View.GONE);
				body.setVisibility(View.GONE);
				order.setVisibility(View.VISIBLE);
				go.setText("订单查询");
				break;

			default:
				break;
			}
		}

		// 默认为0.02
		double getPrice() {
			double price = 0.02;
			try {
				price = Double.parseDouble(this.price.getText().toString());
			} catch (NumberFormatException e) {
			}
			return price;
		}

		// 商品详情(可不填)
		String getName() {
			return this.name.getText().toString();
		}

		// 商品详情(可不填)
		String getBody() {
			return this.body.getText().toString();
		}

		// 支付订单号(查询时必填)
		String getOrder() {
			return this.order.getText().toString();
		}

		void showDialog(String message) {
			try {
				if (dialog == null) {
					dialog = new ProgressDialog(this);
					dialog.setCancelable(true);
				}
				dialog.setMessage(message);
				dialog.show();
			} catch (Exception e) {
				// 在其他线程调用dialog会报错
			}
		}

		void hideDialog() {
			if (dialog != null && dialog.isShowing())
				try {
					dialog.dismiss();
				} catch (Exception e) {
				}
		}

		void installBmobPayPlugin(String fileName) {
			try {
				InputStream is = getAssets().open(fileName);
				File file = new File(Environment.getExternalStorageDirectory()
						+ File.separator + fileName + ".apk");
				if (file.exists())
					file.delete();
				file.createNewFile();
				FileOutputStream fos = new FileOutputStream(file);
				byte[] temp = new byte[1024];
				int i = 0;
				while ((i = is.read(temp)) > 0) {
					fos.write(temp, 0, i);
				}
				fos.close();
				is.close();

				Intent intent = new Intent(Intent.ACTION_VIEW);
				intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
				intent.setDataAndType(Uri.parse("file://" + file),
						"application/vnd.android.package-archive");
				startActivity(intent);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}

}

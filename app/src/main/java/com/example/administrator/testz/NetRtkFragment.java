package com.example.administrator.testz;


import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.suke.widget.SwitchButton;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import dji.common.error.DJIError;
import dji.common.flightcontroller.FlightControllerState;
import dji.common.flightcontroller.RTKState;
import dji.common.flightcontroller.rtk.CoordinateSystem;
import dji.common.flightcontroller.rtk.NetworkServicePlanType;
import dji.common.flightcontroller.rtk.NetworkServicePlansState;
import dji.common.flightcontroller.rtk.NetworkServiceSettings;
import dji.common.flightcontroller.rtk.NetworkServiceState;
import dji.common.flightcontroller.rtk.ReferenceStationSource;
import dji.common.remotecontroller.HardwareState;
import dji.common.remotecontroller.MultiDeviceAggregationState;
import dji.common.remotecontroller.PairingDevice;
import dji.common.util.CommonCallbacks;
import dji.sdk.flightcontroller.FlightController;
import dji.sdk.flightcontroller.RTK;
import dji.sdk.network.RTKNetworkServiceProvider;
import dji.sdk.products.Aircraft;
import dji.sdk.remotecontroller.RemoteController;
import dji.sdk.sdkmanager.DJISDKManager;

public class NetRtkFragment extends Fragment implements View.OnClickListener {
    private RTK mRtk;
    private RTKNetworkServiceProvider mRTKNetworkServiceProvider;
    private TextView infoText;
    private EditText etip;
    private String ipString;

    public NetRtkFragment() {

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_net_rtk, container, false);


        Button btn_check_rtk2 = (Button) view.findViewById(R.id.btn_check_rtk2);
        Button btn_start_rtk2 = (Button) view.findViewById(R.id.btn_start_rtk2);
        Button btn_set_rtk_source2 = (Button) view.findViewById(R.id.btn_set_rtk_source2);
        Button btn_set_net2 = (Button) view.findViewById(R.id.btn_set_net2);
        Button btn_start_net_service2 = (Button) view.findViewById(R.id.btn_start_net_service2);
        Button btn_check_account2 = (Button) view.findViewById(R.id.btn_check_account2);

        Button btn_set_coordinete2 = (Button) view.findViewById(R.id.btn_set_coordinete2);
        Button btn_live_netrtk2 = (Button) view.findViewById(R.id.btn_live_netrtk2);

        etip = (EditText) view.findViewById(R.id.etip);
        infoText = (TextView) view.findViewById(R.id.infoText);

        btn_check_rtk2.setOnClickListener(this);
        btn_start_rtk2.setOnClickListener(this);
        btn_set_rtk_source2.setOnClickListener(this);
        btn_set_net2.setOnClickListener(this);
        btn_start_net_service2.setOnClickListener(this);
        btn_check_account2.setOnClickListener(this);

        btn_set_coordinete2.setOnClickListener(this);
        btn_live_netrtk2.setOnClickListener(this);


        return view;
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        if (ModuleVerificationUtil.isFlightControllerAvailable()) {
            FlightController flightController =
                    ((Aircraft) DJISampleApplication.getProductInstance()).getFlightController();

            flightController.setStateCallback(new FlightControllerState.Callback() {
                @Override
                public void onUpdate(@NonNull FlightControllerState djiFlightControllerCurrentState) {
                    //一开始是连接中  然后就是连接失败？？ 为什么
                    //为什么 是否正在使用RTK  如果RTK连接到飞机   这两个参数一直是false  哪个地方漏了
                    if (ModuleVerificationUtil.isNetRtkAvailable()) {
                        mRTKNetworkServiceProvider = DJISDKManager.getInstance().getRTKNetworkServiceProvider();
                        mRTKNetworkServiceProvider.addNetworkServiceStateCallback(new NetworkServiceState.Callback() {
                            @Override
                            public void onNetworkServiceStateUpdate(NetworkServiceState networkServiceState) {
                                String description5 = String.valueOf(networkServiceState.getChannelState());
                                //  NettyClient.getInstance().sendMsgToServer(description5);
                                changeDescription(description5);
                            }
                        });
                    }

                }
            });
            if (ModuleVerificationUtil.isRtkAvailable()) {
                mRtk = flightController.getRTK();
            }
            if (ModuleVerificationUtil.isNetRtkAvailable()) {
                mRTKNetworkServiceProvider = DJISDKManager.getInstance().getRTKNetworkServiceProvider();
            }
        }


    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_check_rtk2:
                if (ModuleVerificationUtil.isRtkAvailable()) {
                    mRtk = ((Aircraft) DJISampleApplication.getProductInstance()).getFlightController().getRTK();
                    mRtk.setStateCallback(new RTKState.Callback() {
                        @Override
                        public void onUpdate(RTKState rtkState) {
                            String description1 = "AA=当前 RTK 信号是否正在被使用: " + rtkState.isRTKBeingUsed();
                            //   NettyClient.getInstance().sendMsgToServer(description1);
                        }
                    });
                }
                break;
            case R.id.btn_start_rtk2:
                if (ModuleVerificationUtil.isRtkAvailable()) {
                    mRtk = ((Aircraft) DJISampleApplication.getProductInstance()).getFlightController().getRTK();
                    mRtk.setRtkEnabled(true, new CommonCallbacks.CompletionCallback() {
                        @Override
                        public void onResult(DJIError djiError) {

                        }
                    });

                    mRtk.getRtkEnabled(new CommonCallbacks.CompletionCallbackWith<Boolean>() {

                        @Override
                        public void onSuccess(Boolean aBoolean) {
                            //这个东西用来判断是否成功开启RTK
                            String description2 = "AZ=启用RTK模块: " + aBoolean;
                            //  NettyClient.getInstance().sendMsgToServer(description2);
                        }

                        @Override
                        public void onFailure(DJIError djiError) {

                        }
                    });

                }
                break;
            case R.id.btn_set_rtk_source2:
                if (ModuleVerificationUtil.isRtkAvailable()) {
                    mRtk = ((Aircraft) DJISampleApplication.getProductInstance()).getFlightController().getRTK();
                    //设置信号源  网络RTK
                    mRtk.setReferenceStationSource(ReferenceStationSource.CUSTOM_NETWORK_SERVICE, new CommonCallbacks.CompletionCallback() {
                        @Override
                        public void onResult(DJIError djiError) {

                        }
                    });

                    mRtk.addReferenceStationSourceCallback(new ReferenceStationSource.Callback() {
                        @Override
                        public void onReferenceStationSourceUpdate(ReferenceStationSource referenceStationSource) {
                            String description3 = "AC=设置RTK信号源-网络rtk: " + referenceStationSource.toString();
                            //   NettyClient.getInstance().sendMsgToServer(description3);
                        }
                    });
                }
                break;
            case R.id.btn_set_net2:
                if (ModuleVerificationUtil.isNetRtkAvailable()) {
                    mRTKNetworkServiceProvider = DJISDKManager.getInstance().getRTKNetworkServiceProvider();
                    ipString = etip.getText().toString();
                    mRTKNetworkServiceProvider.setCustomNetworkSettings(new NetworkServiceSettings.Builder()
                            .userName("testuser").password("admin123")
                            .ip("59.110.123.145").port(9102).mountPoint("MMPT32_GGB").build()); //设置千寻网络地址

                    String description4 = "AD=网络服务的设置IP: "
                            + mRTKNetworkServiceProvider.getCustomNetworkSettings().getServerAddress()
                            + "，端口号：" + mRTKNetworkServiceProvider.getCustomNetworkSettings().getPort()
                            + "，用户名：" + mRTKNetworkServiceProvider.getCustomNetworkSettings().getUserName()
                            + "，密码：" + mRTKNetworkServiceProvider.getCustomNetworkSettings().getPassword()
                            + "，域名：" + mRTKNetworkServiceProvider.getCustomNetworkSettings().getMountPoint();
                    // NettyClient.getInstance().sendMsgToServer(description4);
                }
                break;
            case R.id.btn_start_net_service2:
                if (ModuleVerificationUtil.isNetRtkAvailable()) {
                    mRTKNetworkServiceProvider = DJISDKManager.getInstance().getRTKNetworkServiceProvider();
                    mRTKNetworkServiceProvider.startNetworkService(new CommonCallbacks.CompletionCallback() {
                        @Override
                        public void onResult(DJIError djiError) {
                            // 启动作为参考站的网络服务
                            //  String description5 = "AE=启用网络服务: " + djiError.toString();
                            //   NettyClient.getInstance().sendMsgToServer(description5);
                        }
                    });
                }
                break;
            case R.id.btn_check_account2:
                if (ModuleVerificationUtil.isNetRtkAvailable()) {
                    mRTKNetworkServiceProvider = DJISDKManager.getInstance().getRTKNetworkServiceProvider();

                    mRTKNetworkServiceProvider.addNetworkServiceStateCallback(new NetworkServiceState.Callback() {
                        @Override
                        public void onNetworkServiceStateUpdate(NetworkServiceState networkServiceState) {
                            String description6 = "AF=检测账户是否可用: " + networkServiceState.getChannelState();
                            //    NettyClient.getInstance().sendMsgToServer(description6);
                        }
                    });
                }
                break;

            case R.id.btn_set_coordinete2:
                // 设置RTK网络服务的坐标系
                if (ModuleVerificationUtil.isNetRtkAvailable()) {
                    mRTKNetworkServiceProvider = DJISDKManager.getInstance().getRTKNetworkServiceProvider();

                    mRTKNetworkServiceProvider.setNetworkServiceCoordinateSystem(CoordinateSystem.CGCS2000, new CommonCallbacks.CompletionCallback() {
                        @Override
                        public void onResult(DJIError djiError) {

                        }
                    });

                    mRTKNetworkServiceProvider.getNetworkServiceCoordinateSystem(new CommonCallbacks.CompletionCallbackWith<CoordinateSystem>() {
                        @Override
                        public void onSuccess(CoordinateSystem coordinateSystem) {
                            //这个东西用来判断设置的坐标系
                            String description2 = "AY=设置的坐标系: " + coordinateSystem;
                            //     NettyClient.getInstance().sendMsgToServer(description2);
                        }

                        @Override
                        public void onFailure(DJIError djiError) {

                        }
                    });

                }
                break;
            case R.id.btn_live_netrtk2:
                //激活网络RTK
                if (ModuleVerificationUtil.isNetRtkAvailable()) {
                    mRTKNetworkServiceProvider = DJISDKManager.getInstance().getRTKNetworkServiceProvider();

                    mRTKNetworkServiceProvider.activateNetworkService(NetworkServicePlanType.A, new CommonCallbacks.CompletionCallback() {
                        @Override
                        public void onResult(DJIError djiError) {

                        }
                    });

                    mRTKNetworkServiceProvider.getNetworkServiceOrderPlans(new CommonCallbacks.CompletionCallbackWith<NetworkServicePlansState>() {
                        @Override
                        public void onSuccess(NetworkServicePlansState networkServicePlansState) {
                            //这获取网络服务计划
                            String description3 = "AQ=获取网络服务计划: " + networkServicePlansState;
                            //     NettyClient.getInstance().sendMsgToServer(description3);
                        }

                        @Override
                        public void onFailure(DJIError djiError) {

                        }
                    });
                }
                break;
        }
    }

    protected void changeDescription(final String newDescription) {
        getActivity().runOnUiThread(new Runnable() {
            @Override
            public void run() {
                infoText.setText(newDescription);
            }
        });
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        if (ModuleVerificationUtil.isFlightControllerAvailable()) {
            ((Aircraft) DJISampleApplication.getProductInstance()).getFlightController().setStateCallback(null);
        }
    }

}

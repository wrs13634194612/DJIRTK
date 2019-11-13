package com.example.administrator.testz;


import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;


import com.suke.widget.SwitchButton;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import dji.common.basestation.BaseStationBatteryState;
import dji.common.basestation.BaseStationState;
import dji.common.error.DJIError;
import dji.common.flightcontroller.FlightControllerState;
import dji.common.flightcontroller.RTKState;
import dji.common.flightcontroller.rtk.ReferenceStationSource;
import dji.common.remotecontroller.MultiDeviceAggregationState;
import dji.common.remotecontroller.PairingDevice;
import dji.common.util.CommonCallbacks;
import dji.sdk.basestation.BaseStation;
import dji.sdk.flightcontroller.FlightController;
import dji.sdk.flightcontroller.RTK;
import dji.sdk.products.Aircraft;
import dji.sdk.remotecontroller.RemoteController;


public class MobileFragment extends Fragment implements View.OnClickListener {
    private RTK mRtk;
    private BaseStation mBaseStation;
    private SwitchButton sw_mobile;
    private TextView tv_rtk_connect;  //连接状态
    private TextView tv_rtk_status;  //rtk模块状态  打开 关闭
    private TextView tv_rtk_source;   //设置信号源
    private TextView tv_point;   //对频
    private TextView tv_isconnected;    //测试 isConnected 方法是否有效
    private TextView tv_current, tv_fv, tv_av, tv_tem, tv_tem5;
    private TextView tv0, tv1, tv2, tv3,
            tv4, tv5, tv6, tv7,
            tv8, tv9, tv10, tv11,
            tv12, tv13, tv14, tv15,
            tv16, tv17, tv18, tv19,
            tv20, tv21, tv22, tv23,
            tv24, tv25, tv26, tv27,
            tv28, tv29, tv30, tv31,
            tv32, tv33, tv34, tv35,
            tv36, tv37, tv38, tv39,
            tv40, tv41, tv42, tv43,
            tv44, tv45, tv46, tv47,
            tv48, tv49;

    //对频
    private Button btn_start_mobile_channel, btn_start_air_channel, btn_stop_channel, btn_restart_channel;
    private RemoteController remoteController;
    private SwitchButton sw_rtk_channel;
    private TextView tv_rtk_more, tv_pairing, tv_channel;


    public MobileFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_mobile, container, false);
        tv_rtk_connect = (TextView) view.findViewById(R.id.tv_rtk_connect);
        tv_rtk_status = (TextView) view.findViewById(R.id.tv_rtk_status);
        tv_rtk_source = (TextView) view.findViewById(R.id.tv_rtk_source);
        tv_current = (TextView) view.findViewById(R.id.tv_current);
        tv_fv = (TextView) view.findViewById(R.id.tv_fv);
        tv_av = (TextView) view.findViewById(R.id.tv_av);
        tv_tem = (TextView) view.findViewById(R.id.tv_tem);
        tv_tem5 = (TextView) view.findViewById(R.id.tv_tem5);
        tv_isconnected = (TextView) view.findViewById(R.id.tv_isconnected);

        sw_mobile = (SwitchButton) view.findViewById(R.id.sw_mobile);

        tv0 = (TextView) view.findViewById(R.id.tv0);
        tv1 = (TextView) view.findViewById(R.id.tv1);
        tv2 = (TextView) view.findViewById(R.id.tv2);
        tv3 = (TextView) view.findViewById(R.id.tv3);
        tv4 = (TextView) view.findViewById(R.id.tv4);

        tv5 = (TextView) view.findViewById(R.id.tv5);
        tv6 = (TextView) view.findViewById(R.id.tv6);
        tv7 = (TextView) view.findViewById(R.id.tv7);
        tv8 = (TextView) view.findViewById(R.id.tv8);
        tv9 = (TextView) view.findViewById(R.id.tv9);

        tv10 = (TextView) view.findViewById(R.id.tv10);
        tv11 = (TextView) view.findViewById(R.id.tv11);
        tv12 = (TextView) view.findViewById(R.id.tv12);
        tv13 = (TextView) view.findViewById(R.id.tv13);
        tv14 = (TextView) view.findViewById(R.id.tv14);

        tv15 = (TextView) view.findViewById(R.id.tv15);
        tv16 = (TextView) view.findViewById(R.id.tv16);
        tv17 = (TextView) view.findViewById(R.id.tv17);
        tv18 = (TextView) view.findViewById(R.id.tv18);
        tv19 = (TextView) view.findViewById(R.id.tv19);

        tv20 = (TextView) view.findViewById(R.id.tv20);
        tv21 = (TextView) view.findViewById(R.id.tv21);
        tv22 = (TextView) view.findViewById(R.id.tv22);
        tv23 = (TextView) view.findViewById(R.id.tv23);
        tv24 = (TextView) view.findViewById(R.id.tv24);

        tv25 = (TextView) view.findViewById(R.id.tv25);
        tv26 = (TextView) view.findViewById(R.id.tv26);
        tv27 = (TextView) view.findViewById(R.id.tv27);
        tv28 = (TextView) view.findViewById(R.id.tv28);
        tv29 = (TextView) view.findViewById(R.id.tv29);

        tv30 = (TextView) view.findViewById(R.id.tv30);
        tv31 = (TextView) view.findViewById(R.id.tv31);
        tv32 = (TextView) view.findViewById(R.id.tv32);
        tv33 = (TextView) view.findViewById(R.id.tv33);
        tv34 = (TextView) view.findViewById(R.id.tv34);

        tv35 = (TextView) view.findViewById(R.id.tv35);
        tv36 = (TextView) view.findViewById(R.id.tv36);
        tv37 = (TextView) view.findViewById(R.id.tv37);
        tv38 = (TextView) view.findViewById(R.id.tv38);
        tv39 = (TextView) view.findViewById(R.id.tv39);

        tv40 = (TextView) view.findViewById(R.id.tv40);
        tv41 = (TextView) view.findViewById(R.id.tv41);
        tv42 = (TextView) view.findViewById(R.id.tv42);
        tv43 = (TextView) view.findViewById(R.id.tv43);
        tv44 = (TextView) view.findViewById(R.id.tv44);

        tv45 = (TextView) view.findViewById(R.id.tv45);
        tv46 = (TextView) view.findViewById(R.id.tv46);
        tv47 = (TextView) view.findViewById(R.id.tv47);
        tv48 = (TextView) view.findViewById(R.id.tv48);
        tv49 = (TextView) view.findViewById(R.id.tv49);


        btn_start_mobile_channel = (Button) view.findViewById(R.id.btn_start_mobile_channel);
        btn_start_air_channel = (Button) view.findViewById(R.id.btn_start_air_channel);
        btn_stop_channel = (Button) view.findViewById(R.id.btn_stop_channel);
        btn_restart_channel = (Button) view.findViewById(R.id.btn_restart_channel);
        sw_rtk_channel = (SwitchButton) view.findViewById(R.id.sw_rtk_channel);
        tv_rtk_more = (TextView) view.findViewById(R.id.tv_rtk_more);  //是否支持多设备连接
        tv_pairing = (TextView) view.findViewById(R.id.tv_pairing);  //设备连接情况
        tv_channel = (TextView) view.findViewById(R.id.tv_channel);  //空中通道

        btn_start_mobile_channel.setOnClickListener(this);
        btn_start_air_channel.setOnClickListener(this);
        btn_stop_channel.setOnClickListener(this);
        btn_restart_channel.setOnClickListener(this);

        sw_mobile.setOnCheckedChangeListener(new SwitchButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(SwitchButton view, boolean isChecked) {
                //开启模块  并且设置网络信号源为移动站
                if (ModuleVerificationUtil.isRtkAvailable()) {
                    mRtk = ((Aircraft) DJISampleApplication.getProductInstance()).getFlightController().getRTK();
                    mRtk.setRtkEnabled(isChecked, new CommonCallbacks.CompletionCallback() {
                        @Override
                        public void onResult(DJIError djiError) {

                        }
                    });

                    mRtk.getRtkEnabled(new CommonCallbacks.CompletionCallbackWith<Boolean>() {

                        @Override
                        public void onSuccess(Boolean aBoolean) {
                            //这个东西用来判断是否成功开启RTK
                            //String description2 = "AZ=启用RTK模块: " + aBoolean;
                            //  NettyClient.getInstance().sendMsgToServer(description2);
                            getActivity().runOnUiThread(new Runnable() {
                                @Override
                                public void run() {
                                    tv_rtk_status.setText(String.valueOf(aBoolean));  //??这玩意能不能记住我的状态呢
                                }
                            });
                        }

                        @Override
                        public void onFailure(DJIError djiError) {
                            String description2 = djiError.getDescription() + ", " + djiError.getErrorCode();
                            //NettyClient.getInstance().sendMsgToServer(description2);
                            tv_rtk_status.setText(description2);
                        }
                    });

                    //设置信号源  移动站
                    mRtk.setReferenceStationSource(ReferenceStationSource.BASE_STATION, new CommonCallbacks.CompletionCallback() {
                        @Override
                        public void onResult(DJIError djiError) {

                        }
                    });

                    mRtk.addReferenceStationSourceCallback(new ReferenceStationSource.Callback() {
                        @Override
                        public void onReferenceStationSourceUpdate(ReferenceStationSource referenceStationSource) {
                            // String description3 = "AC=设置RTK信号源-移动站rtk: " + referenceStationSource.toString();
                            //  NettyClient.getInstance().sendMsgToServer(description3);  tv_rtk_source
                            getActivity().runOnUiThread(new Runnable() {
                                @Override
                                public void run() {
                                    tv_rtk_source.setText(String.valueOf(referenceStationSource));
                                }
                            });
                        }
                    });

                }
            }
        });


        //设置
        sw_rtk_channel.setOnCheckedChangeListener(new SwitchButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(SwitchButton view, boolean isChecked) {
                if (ModuleVerificationUtil.isRemoteControllerAvailable()) {
                    remoteController = ((Aircraft) DJISampleApplication.getProductInstance()).getRemoteController();
                    // 如果为true，则在遥控器上启用RTK通道
                    remoteController.setRTKChannelEnabled(isChecked, new CommonCallbacks.CompletionCallback() {
                        @Override
                        public void onResult(DJIError djiError) {

                        }
                    });
                }
            }
        });
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
                    //一开始是连接中  然后就是连接失败？？ 为什么 为什么 是否正在使用RTK  如果RTK连接到飞机   这两个参数一直是false  哪个地方漏了

                    if (ModuleVerificationUtil.isRtkAvailable()) {
                        mRtk = ((Aircraft) DJISampleApplication.getProductInstance()).getFlightController().getRTK();
                        String description1 = String.valueOf(mRtk.isConnected());
                        /**
                         *    getActivity().runOnUiThread(new Runnable() {
                        @SuppressLint("SetTextI18n")
                        @Override public void run() {
                        tv_isconnected
                         */
                        getActivity().runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                tv_isconnected.setText(description1);
                            }
                        });

                        //    changeContect(description1);
                        mRtk.setStateCallback(new RTKState.Callback() {
                            @Override
                            public void onUpdate(RTKState rtkState) {
                                // String description2 = String.valueOf(rtkState.isRTKBeingUsed());
                                changertkstate(rtkState);

                                String description =
                                        "A=RTKBeingUsed是否正在使用rtk: " + rtkState.isRTKBeingUsed() + "\n"
                                                + "B=distanceToHomePointDataSource到本站数据源的距离: " + rtkState.getDistanceToHomePointDataSource() + "\n"
                                                + "C=TakeoffAltitudeRecorded，true表示飞机起飞时飞行控制器记录了高度\n: " + rtkState.isTakeoffAltitudeRecorded() + "\n" +

                                                "DA=DistanceToHomePointDataSource起始点数据源: " + rtkState.getDistanceToHomePointDataSource() + "\n" +

                                                "DA=DistanceToHomePointDataSource起始点数据源Two: " + rtkState.getHomePointDataSource() +
                                                "DB=HomePointLocation起始点位置: " + rtkState.getHomePointLocation() + "\n" +
                                                "DC=SatelliteCount,GPS或RTK卫星计数: " + rtkState.getSatelliteCount() + "\n"
                                                + "E=TakeOffAltitude起飞高度: " + rtkState.getTakeOffAltitude() + "\n"
                                                + "F=DistanceToHomePoint离家点的距离: " + rtkState.getDistanceToHomePoint() + "\n" +

                                                "G=PositioningSolution描述了用于确定定位的方法: " + rtkState.getPositioningSolution() + "\n"
                                                + "H=Error错误信息: " + rtkState.getError() + "\n"
                                                + "I=HeadingValid航向有效性: " + rtkState.isHeadingValid() + "\n" +

                                                "J=Heading移动站度数: " + rtkState.getHeading() + "\n"
                                                + "K=HeadingSolution确定精度的方法: " + rtkState.getHeadingSolution() + "\n"
                                                + "L=MobileStationLocation指示RTK位置数据: " + rtkState.getMobileStationLocation() + "\n" +

                                                "M=MobileStationAltitude移动台接收器相对于地面系统位置的高度: " + rtkState.getMobileStationAltitude() + "\n"
                                                + "N=MobileStationStandardDeviation以米为单位的定位精度的标准偏差: " + rtkState.getMobileStationStandardDeviation().getStdLatitude()
                                                + "," + rtkState.getMobileStationStandardDeviation().getStdLongitude() + "," + rtkState.getMobileStationStandardDeviation().getStdAltitude() + "\n"
                                                + "O=FusionMobileStationLocation移动台的融合位置: " + rtkState.getFusionMobileStationLocation() + "\n" +

                                                "P=FusionMobileStationAltitude移动台的融合高度: " + rtkState.getFusionMobileStationAltitude() + "\n"
                                                + "Q=FusionHeading移动台的融合航向: " + rtkState.getFusionHeading() + "\n"
                                                + "R=BaseStationLocation基站的位置坐标: " + rtkState.getBaseStationLocation() + "\n" +

                                                "S=BaseStationAltitude基站在海平面以上的高度: " + rtkState.getBaseStationAltitude() + "\n"
                                                + "T=MobileStationReceiver1GPSInfo单个RTK接收器GPS信息,卫星计数: " + rtkState.getMobileStationReceiver1GPSInfo().getSatelliteCount() + "\n"
                                                + "U=MobileStationReceiver1BeiDouInfo单个RTK接收器北斗信息,卫星计数: " + rtkState.getMobileStationReceiver1BeiDouInfo().getSatelliteCount() + "\n" +

                                                "V=MobileStationReceiver1GLONASSInfo 每个接收器连接到单个天线: " + rtkState.getMobileStationReceiver1GLONASSInfo().getSatelliteCount() + "\n"
                                                + "W=MobileStationReceiver1GalileoInfo每个接收器连接到单个天线: " + rtkState.getMobileStationReceiver1GalileoInfo().getSatelliteCount() + "\n"
                                                + "X=MobileStationReceiver2GPSInfo移动台2GPS信息: " + rtkState.getMobileStationReceiver2GPSInfo().getSatelliteCount() + "\n" +

                                                "Y=MobileStationReceiver2BeiDouInfo移动台2北斗信息: " + rtkState.getMobileStationReceiver2BeiDouInfo().getSatelliteCount() + "\n"
                                                + "Z=MobileStationReceiver2GLONASSInfo移动台信息: " + rtkState.getMobileStationReceiver2GLONASSInfo().getSatelliteCount() + "\n"
                                                + "ZA=MobileStationReceiver2GalileoInfo移动台信息: " + rtkState.getMobileStationReceiver2GalileoInfo().getSatelliteCount() + "\n" +

                                                "ZB=BaseStationReceiverGPSInfo: " + rtkState.getBaseStationReceiverGPSInfo().getSatelliteCount() + "\n"
                                                + "ZC=BaseStationReceiverBeiDouInfo: " + rtkState.getBaseStationReceiverBeiDouInfo().getSatelliteCount() + "\n"
                                                + "ZD=BaseStationReceiverGLONASSInfo: " + rtkState.getBaseStationReceiverGLONASSInfo().getSatelliteCount() + "\n" +
                                                "ZE=BaseStationReceiverGalileoInfo: " + rtkState.getBaseStationReceiverGalileoInfo().getSatelliteCount() + "\n"
                                                + "ZF=MobileStationReceiver1GPSInfo: " + rtkState.getMobileStationReceiver1GPSInfo().getSatelliteCount() + "\n"
                                                + "ZG=MobileStationReceiver1BeiDouInfo: " + rtkState.getMobileStationReceiver1BeiDouInfo().getSatelliteCount() + "\n";
                                // NettyClient.getInstance().sendMsgToServer(description);
                            }
                        });

                        mRtk.addReferenceStationSourceCallback(new ReferenceStationSource.Callback() {
                            @Override
                            public void onReferenceStationSourceUpdate(ReferenceStationSource referenceStationSource) {
                                // String description3 = "AC=设置RTK信号源-移动站rtk: " + referenceStationSource.toString();
                                //  NettyClient.getInstance().sendMsgToServer(description3);  tv_rtk_source
                                getActivity().runOnUiThread(new Runnable() {
                                    @Override
                                    public void run() {
                                        tv_rtk_source.setText(String.valueOf(referenceStationSource));
                                    }
                                });
                            }
                        });

                        mRtk.getRtkEnabled(new CommonCallbacks.CompletionCallbackWith<Boolean>() {

                            @Override
                            public void onSuccess(Boolean aBoolean) {
                                //这个东西用来判断是否成功开启RTK
                                //String description2 = "AZ=启用RTK模块: " + aBoolean;
                                //  NettyClient.getInstance().sendMsgToServer(description2);
                                getActivity().runOnUiThread(new Runnable() {
                                    @Override
                                    public void run() {
                                        tv_rtk_status.setText(String.valueOf(aBoolean));  //??这玩意能不能记住我的状态呢
                                    }
                                });
                            }

                            @Override
                            public void onFailure(DJIError djiError) {
                                String description2 = djiError.getDescription() + ", " + djiError.getErrorCode();
                                // NettyClient.getInstance().sendMsgToServer(description2);
                                getActivity().runOnUiThread(new Runnable() {
                                    @Override
                                    public void run() {
                                        tv_rtk_status.setText(description2);
                                    }
                                });

                            }
                        });
                    }

                    if (ModuleVerificationUtil.isBaseStationAvailable()) {
                        mBaseStation = ((Aircraft) DJISampleApplication.getProductInstance()).getBaseStation();
                        mBaseStation.addBaseStationBatteryStateUpdatedCallback(baseStationBatteryState -> {
                            String description5 =
                                    "电压: " + baseStationBatteryState.getVoltage()
                                            + ",电流" + baseStationBatteryState.getCurrent()
                                            + ",电池剩余" + baseStationBatteryState.getCapacityPercent()
                                            + ",温度: " + baseStationBatteryState.getTemperature();
                            //  NettyClient.getInstance().sendMsgToServer(description5);
                            changeDescription2(baseStationBatteryState);
                        });
                        mBaseStation.addBaseStationStateUpdatedCallback(new BaseStationState.Callback() {
                            @Override
                            public void onUpdateBaseStationState(BaseStationState baseStationState) {
                                //添加回调以接收最新的基站状态。 仅受Phantom 4 RTK和M200系列v2支持
                                String description6 =
                                        "电压: " + baseStationState.isAllowedToUse()
                                                + ",基站位置类型" + baseStationState.getBaseStationLocationType();
                                getActivity().runOnUiThread(new Runnable() {
                                    @Override
                                    public void run() {
                                        tv_tem5.setText(description6);
                                    }
                                });
                            }
                        });


                    }

                    //遥控器各种状态
                    if (ModuleVerificationUtil.isRemoteControllerAvailable()) {
                        remoteController = ((Aircraft) DJISampleApplication.getProductInstance()).getRemoteController();
                        //当前是否支持多设备
                        boolean issupport = remoteController.isMultiDevicePairingSupported();
                        getActivity().runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                tv_rtk_more.setText(String.valueOf(issupport));  //是否支持多设备配对
                            }
                        });

                        remoteController.addMultiDevicesPairingStateCallback(new MultiDeviceAggregationState.Callback() {
                            @Override
                            public void onMultiDeviceAggregationStateChanged(MultiDeviceAggregationState multiDeviceAggregationState) {
                                String description2 = "飞机设备状态: " + multiDeviceAggregationState.getAircraftState() + ",RTK: " + multiDeviceAggregationState.getRtkBaseStationState();
                                //   NettyClient.getInstance().sendMsgToServer(description2);
                                getActivity().runOnUiThread(new Runnable() {
                                    @Override
                                    public void run() {
                                        tv_pairing.setText(String.valueOf(description2));  //飞机设备状态
                                    }
                                });
                            }
                        });

                        remoteController.addRTKChannelEnabledUpdatedCallback(new RemoteController.RTKChannelEnabledUpdatedCallback() {
                            @Override
                            public void onRTKChannelEnabledUpdate(boolean b) {
                                getActivity().runOnUiThread(new Runnable() {
                                    @Override
                                    public void run() {
                                        tv_channel.setText(String.valueOf(issupport));  //空中通道
                                    }
                                });

                            }
                        });
                    }

                }
            });
            if (ModuleVerificationUtil.isRtkAvailable()) {
                mRtk = flightController.getRTK();
            }
            if (ModuleVerificationUtil.isBaseStationAvailable()) {
                mBaseStation = ((Aircraft) DJISampleApplication.getProductInstance()).getBaseStation();
            }


        }

    }

    private void changeDescription2(final BaseStationBatteryState baseStationBatteryState) {
        getActivity().runOnUiThread(new Runnable() {
            @Override
            public void run() {
                if (baseStationBatteryState.getVoltage() > 0) {
                    tv_rtk_connect.setText("true");
                } else {
                    tv_rtk_connect.setText("false");
                }
                tv_current.setText(String.valueOf(baseStationBatteryState.getCapacityPercent()));
                tv_fv.setText(String.valueOf(baseStationBatteryState.getVoltage()));
                tv_av.setText(String.valueOf(baseStationBatteryState.getCurrent()));
                tv_tem.setText(String.valueOf(baseStationBatteryState.getTemperature()));
            }
        });

    }

    private void changertkstate(final RTKState rtkState) {
        getActivity().runOnUiThread(new Runnable() {
            @SuppressLint("SetTextI18n")
            @Override
            public void run() {
                //   0A  1B  2C  3D  4E  5F   6G  7H   8I   9J
                tv0.setText("A=" + rtkState.isRTKBeingUsed());
                tv1.setText("B=" + rtkState.getDistanceToHomePointDataSource());


                tv2.setText("C=" + rtkState.isTakeoffAltitudeRecorded());
                tv3.setText("D=" + rtkState.getHomePointDataSource());


                tv4.setText("E=" + rtkState.getSatelliteCount());
                tv5.setText("F=" + rtkState.getHomePointLocation());


                tv6.setText("G=" + rtkState.getTakeOffAltitude());
                tv7.setText("H=" + rtkState.getDistanceToHomePoint());


                tv8.setText("?I=" + rtkState.getPositioningSolution());

                //   tv9.setText("J=" + rtkState.getError().getDescription());


                tv10.setText("BA=" + rtkState.isHeadingValid());
                tv11.setText("BB=" + rtkState.getHeading());

                //   0A  1B  2C  3D  4E  5F   6G  7H   8I   9J
                tv12.setText("?BC=" + rtkState.getHeadingSolution());
                tv13.setText("BD=" + rtkState.getMobileStationLocation().getLatitude());
                tv14.setText("BE=" + rtkState.getMobileStationLocation().getLongitude());


                tv15.setText("BF=" + rtkState.getMobileStationAltitude());

                tv16.setText("BG=" + rtkState.getMobileStationStandardDeviation().getStdLatitude());
                tv17.setText("BH=" + rtkState.getMobileStationStandardDeviation().getStdLongitude());
                tv18.setText("BI=" + rtkState.getMobileStationStandardDeviation().getStdAltitude());

                tv19.setText("BJ=" + rtkState.getFusionMobileStationLocation().getLatitude());
                tv20.setText("CA=" + rtkState.getFusionMobileStationLocation().getLongitude());

                tv21.setText("CB=" + rtkState.getFusionMobileStationAltitude());
                tv22.setText("CC=" + rtkState.getFusionHeading());

                tv23.setText("CD=" + rtkState.getBaseStationLocation().getLatitude());
                tv24.setText("CE=" + rtkState.getBaseStationLocation().getLongitude());
                tv25.setText("CF=" + rtkState.getBaseStationAltitude());

                //   0A  1B  2C  3D  4E  5F   6G  7H   8I   9J
                tv26.setText("CG=" + rtkState.getMobileStationReceiver1GPSInfo().isConstellationSupported());
                tv27.setText("CH=" + rtkState.getMobileStationReceiver1GPSInfo().getSatelliteCount());
                tv28.setText("CI=" + rtkState.getMobileStationReceiver1BeiDouInfo().isConstellationSupported());
                tv29.setText("CJ=" + rtkState.getMobileStationReceiver1BeiDouInfo().getSatelliteCount());

                tv30.setText("DA=" + rtkState.getMobileStationReceiver1GLONASSInfo().isConstellationSupported());
                tv31.setText("DB=" + rtkState.getMobileStationReceiver1GLONASSInfo().getSatelliteCount());

                tv32.setText("DC=" + rtkState.getMobileStationReceiver1GalileoInfo().isConstellationSupported());
                tv33.setText("DD=" + rtkState.getMobileStationReceiver1GalileoInfo().getSatelliteCount());

                tv34.setText("DE=" + rtkState.getMobileStationReceiver2GPSInfo().isConstellationSupported());
                tv35.setText("DF=" + rtkState.getMobileStationReceiver2GPSInfo().getSatelliteCount());

                tv36.setText("DG=" + rtkState.getMobileStationReceiver2BeiDouInfo().isConstellationSupported());
                tv37.setText("DH=" + rtkState.getMobileStationReceiver2BeiDouInfo().getSatelliteCount());
                //   0A  1B  2C  3D  4E  5F   6G  7H   8I   9J
                tv38.setText("DI=" + rtkState.getMobileStationReceiver2GLONASSInfo().isConstellationSupported());
                tv39.setText("DJ=" + rtkState.getMobileStationReceiver2GLONASSInfo().getSatelliteCount());

                tv40.setText("EA=" + rtkState.getMobileStationReceiver2GalileoInfo().isConstellationSupported());
                tv41.setText("EB=" + rtkState.getMobileStationReceiver2GalileoInfo().getSatelliteCount());

                tv42.setText("EC=" + rtkState.getBaseStationReceiverGPSInfo().isConstellationSupported());
                tv43.setText("ED=" + rtkState.getBaseStationReceiverGPSInfo().getSatelliteCount());

                tv44.setText("EE=" + rtkState.getBaseStationReceiverBeiDouInfo().isConstellationSupported());
                tv45.setText("EF=" + rtkState.getBaseStationReceiverBeiDouInfo().getSatelliteCount());

                tv46.setText("EG=" + rtkState.getBaseStationReceiverGLONASSInfo().isConstellationSupported());
                tv47.setText("EH=" + rtkState.getBaseStationReceiverGLONASSInfo().getSatelliteCount());

                tv48.setText("EG=" + rtkState.getBaseStationReceiverGalileoInfo().isConstellationSupported());
                tv49.setText("EH=" + rtkState.getBaseStationReceiverGalileoInfo().getSatelliteCount());

            }
        });
    }


    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_start_mobile_channel:
                if (ModuleVerificationUtil.isRemoteControllerAvailable()) {
                    remoteController = ((Aircraft) DJISampleApplication.getProductInstance()).getRemoteController();
                    //先配置rtk  在配置 飞机
                    remoteController.startMultiDevicePairing(PairingDevice.RTK_BASE_STATION, new CommonCallbacks.CompletionCallback() {
                        @Override
                        public void onResult(DJIError djiError) {

                        }
                    });
                }
                break;
            case R.id.btn_start_air_channel:
                if (ModuleVerificationUtil.isRemoteControllerAvailable()) {
                    remoteController = ((Aircraft) DJISampleApplication.getProductInstance()).getRemoteController();
                    //先配置rtk  在配置 飞机
                    remoteController.startMultiDevicePairing(PairingDevice.AIRCRAFT, new CommonCallbacks.CompletionCallback() {
                        @Override
                        public void onResult(DJIError djiError) {

                        }
                    });
                }
                break;
            case R.id.btn_stop_channel:
                //配对开始后，遥控器与设备成功配对之前使用。 如果遥控器与设备成功配对，则无需调用此方法
                if (ModuleVerificationUtil.isRemoteControllerAvailable()) {
                    remoteController = ((Aircraft) DJISampleApplication.getProductInstance()).getRemoteController();
                    //先配置rtk  在配置 飞机
                    remoteController.stopMultiDevicePairing(new CommonCallbacks.CompletionCallback() {
                        @Override
                        public void onResult(DJIError djiError) {

                        }
                    });
                }
                break;

            case R.id.btn_restart_channel:

                break;
        }
    }


    @Override
    public void onDestroy() {
        super.onDestroy();
        if (ModuleVerificationUtil.isRemoteControllerAvailable()) {
            remoteController = ((Aircraft) DJISampleApplication.getProductInstance()).getRemoteController();

            remoteController.setHardwareStateCallback(null);
        }
    }

}

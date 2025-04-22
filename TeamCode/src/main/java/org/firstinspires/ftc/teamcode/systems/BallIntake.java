package org.firstinspires.ftc.teamcode.systems;

import androidx.annotation.NonNull;

import com.acmerobotics.dashboard.telemetry.TelemetryPacket;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorEx;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.acmerobotics.roadrunner.Action;

public class BallIntake {
    private DcMotorEx motor;
    public boolean isOn = false;
    public BallIntake(HardwareMap hardwareMap) {
        motor = hardwareMap.get(DcMotorEx.class, "intake");
        motor.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        motor.setPower(0);
    }
    public Action toggleIntake(){
        return new Action() {
            @Override
            public boolean run(@NonNull TelemetryPacket telemetryPacket) {
                if(motor.getPower() == 0){
                    motor.setPower(1);
                }else{
                    motor.setPower(0);
                }
                return false;
            }
        };
    }

}

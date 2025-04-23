package org.firstinspires.ftc.teamcode.systems;

import androidx.annotation.NonNull;

import com.acmerobotics.dashboard.telemetry.TelemetryPacket;
import com.acmerobotics.roadrunner.Action;
import com.qualcomm.robotcore.hardware.DcMotorEx;
import com.qualcomm.robotcore.hardware.Gamepad;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.Servo;
public class BlockIntake {
    public Boolean running = false;
    private Servo left;
    private Servo right;
    public double leftSpeed = 1;
    public double rightSpeed = 0;

    public BlockIntake(HardwareMap hardwareMap){
        left=hardwareMap.get(Servo.class,"intakeLeft");
        right=hardwareMap.get(Servo.class,"intakeRight");
    }

    public Action runServos(){
        return new Action() {
            @Override
            public boolean run(@NonNull TelemetryPacket telemetryPacket) {
                if (!running) {
                    left.setPosition(0.5);
                    right.setPosition(0.5);
                } else {
                    left.setPosition(leftSpeed);
                    right.setPosition(rightSpeed);
                }
                return true;
            }
        };
    }
    public Action flipServos() {
        return new Action() {
            @Override
            public boolean run(@NonNull TelemetryPacket telemetryPacket) {
                if (leftSpeed == 1) {
                    leftSpeed = 0;
                    rightSpeed = 1;
                }else{
                    leftSpeed = 1;
                    rightSpeed = 0;
                }
                return false;
            }
        };
    }
    public Action toggleRun(){
        return new Action() {
            @Override
            public boolean run(@NonNull TelemetryPacket telemetryPacket) {
                running=!running;
                return false;
            }
        };

    }
}

package org.firstinspires.ftc.teamcode.systems;

import androidx.annotation.NonNull;

import com.acmerobotics.dashboard.telemetry.TelemetryPacket;
import com.acmerobotics.roadrunner.Action;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.Servo;

public class HopperDoor {

    private Servo servo;
    public boolean isOpen = false;
    public HopperDoor(HardwareMap hardwareMap){
        servo=hardwareMap.get(Servo.class,"door");
    }

    public Action runServo() {
        return new Action() {
            @Override
            public boolean run(@NonNull TelemetryPacket telemetryPacket) {
                if(!isOpen){
                    servo.setPosition(0);
                }else{
                    servo.setPosition(0.5);
                }
                return true;
            }
        };
    }

    public Action toggleOpen() {
        return new Action() {
            @Override
            public boolean run(@NonNull TelemetryPacket telemetryPacket) {
                isOpen = !isOpen;
                return false;
            }
        };
    };
}

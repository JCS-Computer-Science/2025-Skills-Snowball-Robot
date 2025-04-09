package org.firstinspires.ftc.teamcode.systems;

import androidx.annotation.NonNull;

import com.acmerobotics.dashboard.telemetry.TelemetryPacket;
import com.acmerobotics.roadrunner.Action;
import com.qualcomm.robotcore.hardware.DcMotorEx;
import com.qualcomm.robotcore.hardware.Gamepad;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.Servo;

public class Intake {
    private Servo servo;
    public Intake(HardwareMap hardwareMap){
        servo=hardwareMap.get(Servo.class,"intake");
        //90deg left=0.6
    }
    public double getPosition(){
        return servo.getPosition();
    }
    public class SetServo implements Action {
        private double position;
        private boolean initialized = false;
        public SetServo(double position){
            this.position=position;
        }
        @Override
        public boolean run(@NonNull TelemetryPacket telemetryPacket) {
            if(!this.initialized){
                servo.setPosition(position);
                initialized=true;
            }
            return false;
        }
    }

    public Action setServo(double position){
        return new SetServo(position);
    }

    public class IntakeControl implements Action{
        private Gamepad controller;

        public IntakeControl(Gamepad c){
            controller=c;
        }

        @Override
        public boolean run(@NonNull TelemetryPacket telemetryPacket) {
            servo.setPosition(Math.max(0,Math.min(1,(controller.right_trigger-controller.left_trigger+1)*0.5)));
            return true;
        }
    }
    public Action intakeControl(Gamepad c){return new IntakeControl(c);}
}

package org.firstinspires.ftc.teamcode.systems;

import androidx.annotation.NonNull;

import com.acmerobotics.dashboard.telemetry.TelemetryPacket;
import com.acmerobotics.roadrunner.Action;
import com.qualcomm.robotcore.hardware.DcMotorEx;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.Servo;

public class Claw {


    private Servo servo;

    public Claw(HardwareMap hardwareMap){

        servo=hardwareMap.get(Servo.class,"Claw");
    }
    public double getPosition(){return servo.getPosition();}
    public class SetServo implements Action {
        private double position;
        private boolean initialized = false;
        public SetServo(double position){
            this.position=position;
        }
        @Override
        public boolean run(@NonNull TelemetryPacket telemetryPacket) {

                if(servo.getPosition() == position) {
                    servo.setPosition(0.3);
                } else {
                    servo.setPosition(position);
                }

            return false;
        }
    }

    public Action setServo(double position){
        return new SetServo(position);
    }
}

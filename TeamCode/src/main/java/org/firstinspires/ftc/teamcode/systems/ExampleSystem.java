package org.firstinspires.ftc.teamcode.systems;

import androidx.annotation.NonNull;

import com.acmerobotics.dashboard.telemetry.TelemetryPacket;
import com.acmerobotics.roadrunner.Action;
import com.qualcomm.robotcore.hardware.DcMotorEx;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.Servo;

public class ExampleSystem {

    public enum POSITION {
        OPEN(1),
        MIDDLE(0.5),

        CLOSED(0);

        public final double position;

        POSITION(double position){
            this.position=position;
        }

    }

    private Servo servo;

    public ExampleSystem(HardwareMap hardwareMap){

        servo=hardwareMap.get(Servo.class,"servo");
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
}

package org.firstinspires.ftc.teamcode.systems;

import androidx.annotation.NonNull;

import com.acmerobotics.dashboard.telemetry.TelemetryPacket;
import com.acmerobotics.roadrunner.Action;
import com.qualcomm.robotcore.hardware.DcMotorEx;
import com.qualcomm.robotcore.hardware.HardwareMap;

public class ExampleSystem {

    private DcMotorEx motor;

    public ExampleSystem(HardwareMap hardwareMap){
        motor = hardwareMap.get(DcMotorEx.class, "motor");
    }

    public class SetMotor implements Action {
        private double speed;
        private boolean initialized = false;
        public SetMotor(double speed){
            super();
            this.speed=speed;
        }
        @Override
        public boolean run(@NonNull TelemetryPacket telemetryPacket) {
            if(!this.initialized){
                motor.setPower(this.speed);
                initialized=true;
            }
            return false;
        }
    }

    public Action setMotor(double speed){
        return new SetMotor(speed);
    }
}

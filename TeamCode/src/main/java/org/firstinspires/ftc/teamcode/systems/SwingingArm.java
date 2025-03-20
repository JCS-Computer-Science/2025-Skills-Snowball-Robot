package org.firstinspires.ftc.teamcode.systems;

import android.text.util.Linkify;

import androidx.annotation.NonNull;

import com.acmerobotics.dashboard.telemetry.TelemetryPacket;
import com.acmerobotics.roadrunner.Action;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorEx;
import com.qualcomm.robotcore.hardware.HardwareMap;

public class SwingingArm {
    private DcMotorEx motor;

    public SwingingArm(HardwareMap hardwareMap) {
        motor = hardwareMap.get(DcMotorEx.class, "swingingArm");
        motor.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        motor.setPositionPIDFCoefficients(3);
        motor.setTargetPosition(0);
        motor.setTargetPositionTolerance(20);
    }

    public class SetPosition implements Action {
        private int position;
        private boolean wait;
        private boolean initialized = false;
        public SetPosition(int position, boolean wait){
            this.position=position;
            this.wait = wait;
        }
        public SetPosition(int position){
            this(position, true);
        }

        @Override
        public boolean run(@NonNull TelemetryPacket telemetryPacket) {
            if (!this.initialized) {
                if (motor.getTargetPosition() == position){
                    motor.setTargetPosition(0);
                } else {
                    motor.setTargetPosition(position);
                }
                motor.setTargetPositionTolerance(5);
                motor.setMode(DcMotorEx.RunMode.RUN_TO_POSITION);
                motor.setPower(1);
                initialized = true;
                telemetryPacket.addLine("Swinging arm moving to position " + position);
            }
            if (this.position != motor.getTargetPosition()) {
                //if another target got set by another action, stop this action
                return false;
            }
            return wait ? motor.isBusy() : false;
        }
    }

    public int getPosition() {
        return motor.getCurrentPosition();
    }

    public Action setPosition(int position, boolean wait){
        return new SetPosition(position, wait);
    }
    public Action setPosition(int position){
        return new SetPosition(position);
    }

    public Action resetEncoder(){
        return new Action() {
            @Override
            public boolean run(@NonNull TelemetryPacket telemetryPacket) {
                motor.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
                return false;
            }
        };
    }

}
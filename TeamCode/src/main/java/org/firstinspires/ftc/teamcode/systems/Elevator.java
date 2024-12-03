package org.firstinspires.ftc.teamcode.systems;

import androidx.annotation.NonNull;

import com.acmerobotics.dashboard.telemetry.TelemetryPacket;
import com.acmerobotics.roadrunner.Action;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorEx;
import com.qualcomm.robotcore.hardware.HardwareMap;

public class Elevator {
    private DcMotorEx motor;

    public enum POSITION {
        TOP(-4270),
        BOTTOM(0);

        public final int ticks;

        POSITION(int ticks){
            this.ticks=ticks;
        }

    }
    public Elevator(HardwareMap hardwareMap){
        motor=hardwareMap.get(DcMotorEx.class,"elevator");
        motor.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        motor.setPositionPIDFCoefficients(8);
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
            if(!this.initialized){
                motor.setTargetPosition(position);
                motor.setMode(DcMotorEx.RunMode.RUN_TO_POSITION);
                motor.setPower(1);
                initialized=true;
                telemetryPacket.addLine("Elevator moving to position "+position);
            }
            if(this.position!=motor.getTargetPosition()){
                //if another target got set by another action, stop this action
                return false;
            }
            return wait ? motor.isBusy() : false;
        }
    }

    public int getPosition(){
        return motor.getCurrentPosition();
    }

    public Action setPosition(int position, boolean wait){
        return new SetPosition(position, wait);
    }
    public Action setPosition(int position){
        return new SetPosition(position);
    }

}
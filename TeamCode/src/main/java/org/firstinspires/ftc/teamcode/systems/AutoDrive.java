package org.firstinspires.ftc.teamcode.systems;

import androidx.annotation.NonNull;

import com.acmerobotics.dashboard.telemetry.TelemetryPacket;
import com.acmerobotics.roadrunner.Action;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorEx;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.util.ElapsedTime;

public class AutoDrive {
    private DcMotorEx left;
    private DcMotorEx right;
    public boolean reversed = false;
    private ElapsedTime time = new ElapsedTime();
    public AutoDrive(HardwareMap hardwareMap){
        left = hardwareMap.get(DcMotorEx.class, "leftMotor");
        left.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        left.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        left.setPower(0);
        right = hardwareMap.get(DcMotorEx.class, "rightMotor");
        right.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        right.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        right.setPower(0);
    }
    public Action startMotors() {
        return new Action() {
            @Override
            public boolean run(@NonNull TelemetryPacket telemetryPacket) {
                left.setPower(0.8);
                right.setPower(0.8);
                time.reset();
                return false;
            }
        };
    }
    public Action runMotors() {
        return new Action() {
            @Override
            public boolean run(@NonNull TelemetryPacket telemetryPacket) {
                if(time.seconds() == 5) {
                    if (reversed) {
                        left.setDirection(DcMotorSimple.Direction.FORWARD);
                        right.setDirection(DcMotorSimple.Direction.FORWARD);
                        reversed = false;
                        time.reset();
                    } else {
                        left.setDirection(DcMotorSimple.Direction.REVERSE);
                        right.setDirection(DcMotorSimple.Direction.REVERSE);
                        reversed = true;
                        time.reset();
                    }
                }
                return true;
            }
        };
    }
}

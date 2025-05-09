package org.firstinspires.ftc.teamcode;

import androidx.annotation.NonNull;

import com.acmerobotics.dashboard.config.Config;
import com.acmerobotics.dashboard.telemetry.TelemetryPacket;
import com.acmerobotics.roadrunner.Action;
import com.acmerobotics.roadrunner.DualNum;
import com.acmerobotics.roadrunner.PoseVelocity2d;
import com.acmerobotics.roadrunner.PoseVelocity2dDual;
import com.acmerobotics.roadrunner.TankKinematics;
import com.acmerobotics.roadrunner.Time;
import com.acmerobotics.roadrunner.Vector2d;
import com.acmerobotics.roadrunner.ftc.LynxFirmware;
import com.qualcomm.hardware.lynx.LynxModule;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorEx;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.VoltageSensor;

import java.util.Arrays;
import java.util.List;

@Config
public final class TankDrive {
    public int slowMode = 0;
    public final List<DcMotorEx> leftMotors, rightMotors;
    public final VoltageSensor voltageSensor;
    public TankDrive(HardwareMap hardwareMap) {
        LynxFirmware.throwIfModulesAreOutdated(hardwareMap);
        for (LynxModule module : hardwareMap.getAll(LynxModule.class)) {
            module.setBulkCachingMode(LynxModule.BulkCachingMode.AUTO);
        }

        // TODO: make sure your config has motors with these names (or change them)
        //   add additional motors on each side if you have them
        //   see https://ftc-docs.firstinspires.org/en/latest/hardware_and_software_configuration/configuring/index.html
        leftMotors = Arrays.asList(hardwareMap.get(DcMotorEx.class, "left"));
        rightMotors = Arrays.asList(hardwareMap.get(DcMotorEx.class, "right"));

        for (DcMotorEx m : leftMotors) {
            m.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        }
        for (DcMotorEx m : rightMotors) {
            m.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        }

        // TODO: reverse motor directions if needed
        rightMotors.get(0).setDirection(DcMotorSimple.Direction.REVERSE);

        voltageSensor = hardwareMap.voltageSensor.iterator().next();

    }

    public void setDrivePowers(PoseVelocity2d powers) {
        TankKinematics.WheelVelocities<Time> wheelVels = new TankKinematics(2).inverse(
                PoseVelocity2dDual.constant(powers, 1));

        double maxPowerMag = 1;
        for (DualNum<Time> power : wheelVels.all()) {
            maxPowerMag = Math.max(maxPowerMag, power.value());
        }

        for (DcMotorEx m : leftMotors) {
            m.setPower(wheelVels.left.get(0) / maxPowerMag);
        }
        for (DcMotorEx m : rightMotors) {
            m.setPower(wheelVels.right.get(0) / maxPowerMag);
        }
    }
    private final class DriveAction implements Action {
        GamepadEx driver;

        public DriveAction(GamepadEx gamepad){
            driver=gamepad;
        }
        @Override
        public boolean run(@NonNull TelemetryPacket telemetryPacket) {
            if(slowMode == 0) {
                setDrivePowers(new PoseVelocity2d(
                        new Vector2d(
                                -(driver.gamepad.right_trigger - driver.gamepad.left_trigger),
                                -0
                        ),
                        driver.gamepad.right_stick_x * 0.5
                ));
            }else if(slowMode == 1){
                setDrivePowers(new PoseVelocity2d(
                        new Vector2d(
                                -(driver.gamepad.right_trigger - driver.gamepad.left_trigger)*0.5,
                                -0
                        ),
                        driver.gamepad.right_stick_x * 0.25
                ));
            }else{
                setDrivePowers(new PoseVelocity2d(
                        new Vector2d(
                                -(driver.gamepad.right_trigger - driver.gamepad.left_trigger)*0.25,
                                -0
                        ),
                        driver.gamepad.right_stick_x * 0.125
                ));
            }
            return true;
        }
    }
    public DriveAction driveAction(GamepadEx gamepad){
        return new DriveAction(gamepad);
    }

    public Action toggleSlowMode(){
        return new Action() {
            @Override
            public boolean run(@NonNull TelemetryPacket telemetryPacket) {
                if(slowMode < 2){
                    slowMode++;
                }else{
                    slowMode = 0;
                }
                return false;
            }
        };


    }






}

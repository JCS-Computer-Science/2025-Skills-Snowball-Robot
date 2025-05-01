package org.firstinspires.ftc.teamcode.auto;

import com.acmerobotics.roadrunner.ftc.Actions;
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;

import org.firstinspires.ftc.teamcode.systems.AutoDrive;

@Autonomous(name="Auto Robot")
public class AutoRobot extends LinearOpMode{
    @Override
    public void runOpMode() throws InterruptedException {
        AutoDrive drive = new AutoDrive(hardwareMap);
        waitForStart();
        drive.startMotors();
        while (isStarted()) {
            drive.runMotors();
        }
    }
}

package org.firstinspires.ftc.teamcode.auto;

import com.acmerobotics.dashboard.FtcDashboard;
import com.acmerobotics.roadrunner.Action;
import com.acmerobotics.roadrunner.ftc.Actions;
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;

import org.firstinspires.ftc.teamcode.systems.AutoDrive;

import java.util.ArrayList;
import java.util.List;

@Autonomous(name="Auto Robot")
public class AutoRobot extends OpMode{
    private FtcDashboard dash = FtcDashboard.getInstance();
    private List<Action> runningActions = new ArrayList<>();
    public AutoDrive drive;
    @Override
    public void init(){
        drive= new AutoDrive(hardwareMap);
        runningActions.add(drive.startMotors());
    }

    @Override
    public void loop(){
        runningActions.add(drive.runMotors());
    }
}

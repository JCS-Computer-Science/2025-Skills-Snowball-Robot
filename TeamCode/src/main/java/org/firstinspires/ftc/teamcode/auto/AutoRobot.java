package org.firstinspires.ftc.teamcode.auto;

import com.acmerobotics.dashboard.FtcDashboard;
import com.acmerobotics.dashboard.telemetry.TelemetryPacket;
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
//    public void start(){
//        runningActions.add(drive.runMotors());
//    }
    @Override
    public void loop(){
        TelemetryPacket packet = new TelemetryPacket();
        runningActions.add(drive.runMotors());
        telemetry.addData("time", drive.time);
        telemetry.addData("reversed?", drive.reversed);
        updateActions(packet);
    }

    private void updateActions(TelemetryPacket packet){
        List<Action> newActions = new ArrayList<>();
        for(Action action : runningActions){
            action.preview(packet.fieldOverlay());
            if(action.run(packet)){
                newActions.add(action);
            }
            runningActions = newActions;

        }
    }
}

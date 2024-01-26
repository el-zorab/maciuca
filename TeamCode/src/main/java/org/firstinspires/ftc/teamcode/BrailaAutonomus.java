package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.util.ElapsedTime;

@Autonomous
public class BrailaAutonomus extends LinearOpMode {

    private double puterebrat = 1;
    private double position = 0;
    private double opritor = 1;


    private ElapsedTime runtime = new ElapsedTime();
    private DcMotor leftFrontDrive = null;
    private DcMotor leftBackDrive = null;
    private DcMotor rightFrontDrive = null;
    private DcMotor rightBackDrive = null;
    private DcMotor Brat = null;
    private DcMotor Lansator = null;
    private Servo Propulsor = null;
    private DcMotor Intake = null;
    private Servo Rotator = null;

    @Override
    public void runOpMode() {

        leftFrontDrive  = hardwareMap.get(DcMotor.class, "left_front_drive");
        leftBackDrive  = hardwareMap.get(DcMotor.class, "left_back_drive");
        rightFrontDrive = hardwareMap.get(DcMotor.class, "right_front_drive");
        rightBackDrive = hardwareMap.get(DcMotor.class, "right_back_drive");
        Brat = hardwareMap.get(DcMotor.class,"Brat");
        Brat.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        Brat.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        Brat.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        Lansator = hardwareMap.get(DcMotor.class,"Lansator");
        Lansator.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        Lansator.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        Intake = hardwareMap.get(DcMotor.class,"Intake");
        Intake.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        Intake.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        Rotator = hardwareMap.get(Servo.class,"Rotator");
        Propulsor = hardwareMap.get(Servo.class,"Propulsor");


        leftFrontDrive.setDirection(DcMotor.Direction.REVERSE);
        leftBackDrive.setDirection(DcMotor.Direction.REVERSE);
        rightFrontDrive.setDirection(DcMotor.Direction.FORWARD);
        rightBackDrive.setDirection(DcMotor.Direction.FORWARD);

        // Wait for the game to start (driver presses PLAY)
        telemetry.addData("Status", "Initialized");
        telemetry.update();

        waitForStart();
        runtime.reset();

        while (opModeIsActive()){



        }
    }
}
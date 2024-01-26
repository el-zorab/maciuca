package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.util.ElapsedTime;

@Autonomous
public class GalatiAutonomusDreapta extends LinearOpMode {

    private ElapsedTime runtime = new ElapsedTime();
    private DcMotor leftFrontDrive = null;
    private DcMotor leftBackDrive = null;
    private DcMotor rightFrontDrive = null;
    private DcMotor rightBackDrive = null;
    private DcMotor brat = null;
    private DcMotor Lansator = null;
    private Servo Propulsor = null;
    private Servo ServoMiscareGheara = null;
    private Servo  ServoGhearaStanga = null;
    private Servo ServoGhearaDreapta = null;

    @Override
    public void runOpMode() {

        leftFrontDrive  = hardwareMap.get(DcMotor.class, "left_front_drive");
        leftBackDrive  = hardwareMap.get(DcMotor.class, "left_back_drive");
        rightFrontDrive = hardwareMap.get(DcMotor.class, "right_front_drive");
        rightBackDrive = hardwareMap.get(DcMotor.class, "right_back_drive");
        brat = hardwareMap.get(DcMotor.class,"Brat");
        brat.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        brat.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        brat.setDirection(DcMotorSimple.Direction.FORWARD);
        brat.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        Lansator = hardwareMap.get(DcMotor.class,"Lansator");
        Lansator.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        ServoGhearaStanga = hardwareMap.get(Servo.class, "ServoGhearaStanga");
        ServoGhearaDreapta = hardwareMap.get(Servo.class,"ServoghearaDreapta");
        ServoMiscareGheara = hardwareMap.get(Servo.class,"ServoMiscareGheara");
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

            leftFrontDrive.setPower(-1);
            leftBackDrive.setPower(1);
            rightFrontDrive.setPower(1);
            rightBackDrive.setPower(-1);
            sleep(1000);

            leftFrontDrive.setPower(0);
            leftBackDrive.setPower(0);
            rightFrontDrive.setPower(0);
            rightBackDrive.setPower(0);
            sleep(27000);
        }
   }
}
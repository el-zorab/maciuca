package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.util.ElapsedTime;

@Autonomous
public class GalatiAutonomusStanga extends LinearOpMode {

    private boolean ready1 = false;
    private boolean ready2 = false;
    private boolean open1 = true;
    private boolean open2 = true;
    private double puterebrat = 1;
    private double position = 1;
    private double pozitie1 = 0.50;
    private double pozitie2 = 0.50;
    private double opritor = 1;
    private double count = 1;
    private double isgamepadedy = 0;
    private double isgamepadedx = 0;


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
        ServoGhearaDreapta.setPosition(1);
        ServoGhearaStanga.setPosition(-1);

        while (opModeIsActive()){

            ServoGhearaDreapta.setPosition(1);
            ServoGhearaStanga.setPosition(1);

            leftFrontDrive.setPower(0.5);
            leftBackDrive.setPower(0.5);
            rightFrontDrive.setPower(0.5);
            rightBackDrive.setPower(1);
            sleep(600);

            leftFrontDrive.setPower(1);
            leftBackDrive.setPower(-1);
            rightFrontDrive.setPower(-1);
            rightBackDrive.setPower(1);
            sleep(1200);

            leftFrontDrive.setPower(0);
            leftBackDrive.setPower(0);
            rightFrontDrive.setPower(0);
            rightBackDrive.setPower(0);
            sleep(28000);

        }
    }
}
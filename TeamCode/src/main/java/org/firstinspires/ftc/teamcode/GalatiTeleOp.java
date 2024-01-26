package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.util.ElapsedTime;
import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.util.Range;

import org.firstinspires.ftc.robotcore.external.navigation.Position;


@TeleOp

public class GalatiTeleOp extends LinearOpMode {

    private boolean ready1 = false;
    private boolean ready2 = false;
    private boolean open1 = true;
    private boolean open2 = true;
    private double puterebrat = 1;
    private double position = 1;
    private double pozitie1 = 1;
    private double pozitie2 = 1;
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

        // Initialize the hardware variables. Note that the strings used here must correspond
        // to the names assigned during the robot configuration step on the DS or RC devices.
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


        while (opModeIsActive()) {
            double max;

            // POV Mode uses left joystick to go forward & strafe, and right joystick to rotate.
            double axial = gamepad1.left_stick_y + isgamepadedy;  // Note: pushing stick forward gives negative value
            double lateral = -gamepad1.left_stick_x + isgamepadedx;
            double yaw = -gamepad1.right_stick_x;

            // Combine the joystick requests for each axis-motion to determine each wheel's power.
            // Set up a variable for each drive wheel to save the power level for telemetry.
            double leftFrontPower = axial + lateral + yaw;
            double rightFrontPower = axial - lateral - yaw;
            double leftBackPower = axial - lateral + yaw;
            double rightBackPower = axial + lateral - yaw;

            // Normalize the values so no wheel power exceeds 100%
            // This ensures that the robot maintains the desired motion.
            max = Math.max(Math.abs(leftFrontPower), Math.abs(rightFrontPower));
            max = Math.max(max, Math.abs(leftBackPower));
            max = Math.max(max, Math.abs(rightBackPower));

            if (max > 1.0) {
                leftFrontPower /= max;
                rightFrontPower /= max;
                leftBackPower /= max;
                rightBackPower /= max;
            }

            if (gamepad2.right_trigger > 0) {
                puterebrat = 0.2;
            } else {
                puterebrat = 1;
            }

            if (gamepad2.dpad_up) {
                position += 0.01;
            } else if (gamepad2.dpad_down) {
                position -= 0.01;
            }
            position = Range.clip(position, 0, 1);
            ServoMiscareGheara.setPosition(position);
            if (gamepad2.dpad_right) {
                brat.setPower(1 * puterebrat);
            } else if (gamepad2.dpad_left) {
                brat.setPower(-1 * puterebrat);
            } else {
                brat.setPower(0);
            }
            /*if (gamepad1.right_trigger > 0) {
                opritor = 0.3;
            } else {
                opritor = 1;
            }
            else if (gamepad1.left_trigger > 0) {
                opritor = 0.6;
            } else {
                opritor = 1;
            }*/

            if (gamepad1.right_trigger > 0) {
                opritor = 0.3;
            } else if(gamepad1.left_trigger > 0){
                opritor = 0.6;
            } else {
                opritor = 1;
            }

            if (!gamepad2.right_bumper) {
                ready2 = true;
            }
            if (gamepad2.right_bumper && ready2) {
                ready2 = false;
                if (open2) {
                    open2 = false;
                    pozitie2 = 1;
                } else if (!open2) {
                    open2 = true;
                    pozitie2 = 0;
                }
            }
            if (!gamepad2.left_bumper) {
                ready1 = true;
            }
            if (gamepad2.left_bumper && ready1) {
                ready1 = false;
                if (open1) {
                    open1 = false;
                    pozitie1 = 0;
                } else if (!open1) {
                    open1 = true;
                    pozitie1 = 1;
                }
            }


            if(gamepad2.triangle){
                if(count == 1){
                    brat.setTargetPosition(9512);
                    count++;
                }
                else if (count==2){
                    brat.setTargetPosition(9436);
                    count++;
                }
                else if (count == 3){
                    brat.setTargetPosition(9330);
                    count++;
                }
                else if (count == 4){
                    brat.setTargetPosition(9276);
                    count++;
                }
                else if (count==5){
                    brat.setTargetPosition(9210);
                    count = 1;
                }
                brat.setMode(DcMotor.RunMode.RUN_TO_POSITION);
                brat.setPower(1);
                while (opModeIsActive() && brat.isBusy() && Math.abs(brat.getTargetPosition() - brat.getCurrentPosition()) > 30) {
                    /*telemetry.addData("Pozitie brat", brat.getCurrentPosition());
                    telemetry.update();*/
                }
                brat.setPower(0);
                brat.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
            }

            if (gamepad1.dpad_up){
                isgamepadedy = -1;
            }
            if (gamepad1.dpad_down){
                isgamepadedy = 1;
            }
            if (gamepad1.dpad_left){
                isgamepadedx = 1;
            }
            if (gamepad1.dpad_right){
                isgamepadedx = -1;
            }
            if (!gamepad1.dpad_up && !gamepad1.dpad_down){
                isgamepadedy = 0;
            }
            if (!gamepad1.dpad_left && !gamepad1.dpad_right){
                isgamepadedx = 0;
            }

            /*if (gamepad2.triangle) {
                brat.setTargetPosition(9512);
                brat.setMode(DcMotor.RunMode.RUN_TO_POSITION);
                brat.setPower(1);
                while (opModeIsActive() && brat.isBusy() && Math.abs(brat.getTargetPosition() - brat.getCurrentPosition()) > 30) {

                }
                brat.setPower(0);
                brat.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
                //ServoMiscareGheara.setPosition(0.21);
            }
            if (gamepad2.circle) {
                brat.setTargetPosition(9436);
                brat.setMode(DcMotor.RunMode.RUN_TO_POSITION);
                brat.setPower(1);
                while (opModeIsActive() && brat.isBusy() && Math.abs(brat.getTargetPosition() - brat.getCurrentPosition()) > 30) {
                }
                brat.setPower(0);
                brat.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
                //ServoMiscareGheara.setPosition(0.19);
            }
            if (gamepad2.cross) {
                brat.setTargetPosition(9330);
                brat.setMode(DcMotor.RunMode.RUN_TO_POSITION);
                brat.setPower(1);
                while (opModeIsActive() && brat.isBusy() && Math.abs(brat.getTargetPosition() - brat.getCurrentPosition()) > 30) {
                }
                brat.setPower(0);
                brat.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
                //ServoMiscareGheara.setPosition(0.19);
            }
            if (gamepad2.square) {
                brat.setTargetPosition(9276);
                brat.setMode(DcMotor.RunMode.RUN_TO_POSITION);
                brat.setPower(1);
                while (brat.isBusy() && brat.isBusy() && Math.abs(brat.getTargetPosition() - brat.getCurrentPosition()) > 30) {
                }
                brat.setPower(0);
                brat.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
                //ServoMiscareGheara.setPosition(0.16);
            }


             */
            ServoGhearaDreapta.setPosition(pozitie1);
            ServoGhearaStanga.setPosition(pozitie2);
            leftFrontDrive.setPower(leftFrontPower * opritor);
            rightFrontDrive.setPower(rightFrontPower * opritor);
            leftBackDrive.setPower(leftBackPower * opritor);
            rightBackDrive.setPower(rightBackPower * opritor);

            if (gamepad1.triangle && gamepad1.square) {
                Lansator.setPower(1);
                sleep(1000);
                Propulsor.setPosition(0);
            }


            if (gamepad1.circle) {
                Lansator.setPower(0);
                Propulsor.setPosition(1);
            }

            // Show the elapsed game time and wheel power.
            telemetry.addData("Status", "Run Time: " + runtime.toString());
            telemetry.addData("Front left/Right", "%4.2f, %4.2f", leftFrontPower, rightFrontPower);
            telemetry.addData("Back  left/Right", "%4.2f, %4.2f", leftBackPower, rightBackPower);
            telemetry.addData("Pozitie brat", brat.getCurrentPosition());
            telemetry.addData("pozitie Servo", ServoMiscareGheara.getPosition());
            telemetry.update();

        }

        //*brat.setTargetPosition(0);
        //brat.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        //brat.setPower(1);
        //.setPower(0);


    }

}
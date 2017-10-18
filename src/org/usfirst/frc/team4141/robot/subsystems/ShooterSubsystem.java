package org.usfirst.frc.team4141.robot.subsystems;

import org.usfirst.frc.team4141.MDRobotBase.MDRobotBase;
import org.usfirst.frc.team4141.MDRobotBase.MDSubsystem;
import org.usfirst.frc.team4141.MDRobotBase.config.ConfigSetting;
import edu.wpi.first.wpilibj.Solenoid;
import edu.wpi.first.wpilibj.SolenoidBase;
import org.usfirst.frc.team4141.MDRobotBase.sensors.MDDigitalInput;


import edu.wpi.first.wpilibj.SpeedController;

public class ShooterSubsystem extends MDSubsystem {
	
	private double shootSpeed = 1;
	
	public enum SwitchPosition{
		extended,
		retracted
	}
	
	public static String solenoid1="Solenoid1";
	
	private SpeedController shooterController1;
	private SpeedController shooterController2;
	

	public static String motorName1="shootermotor1";
	public static String motorName2="shootermotor2";


	public MDSubsystem configure(){
		super.configure();

		if(getSolenoids()==null 
				|| !getSolenoids().containsKey(solenoid1))
			throw new IllegalArgumentException("Invalid solenoid configuration for shoot system.");
		
		if(getSolenoids()==null 
				|| !getSolenoids().containsKey(solenoid1))
			throw new IllegalArgumentException("Invalid solenoid configuration for shoot system.");
		
		if(getMotors()==null 
				|| !getMotors().containsKey(motorName1))
			throw new IllegalArgumentException("Invalid motor configuration for shoot system.");
		shooterController1 = (SpeedController)(getMotors().get(motorName1));
		
		if(getMotors()==null 
				|| !getMotors().containsKey(motorName2))
			throw new IllegalArgumentException("Invalid motor configuration for shoot system.");
		shooterController2 = (SpeedController)(getMotors().get(motorName2));
		return this;
	}
	
	public ShooterSubsystem(MDRobotBase robot, String name) {
		super(robot, name);
		// TODO Auto-generated constructor stub
	}
	
	public void shoot(){
		shooterController1.set(shootSpeed);
		shooterController2.set(-shootSpeed);
	}
		
	public void stop(){
		shooterController1.stopMotor();
		shooterController2.stopMotor();
		
	}
	
	@Override
	protected void setUp() {
		
		if(getConfigSettings().containsKey("shootSpeed")) shootSpeed = getConfigSettings().get("shootSpeed").getDouble();
		
	}

	@Override
	public void settingChangeListener(ConfigSetting changedSetting) {
		
		if(changedSetting.getName().equals("shootSpeed")) shootSpeed = changedSetting.getDouble();

	}

	@Override
	protected void initDefaultCommand() {
		// TODO Auto-generated method stub

	}
	
	public void open(){
		Solenoid piston = (Solenoid)getSolenoids().get(solenoid1);
		piston.set(true);
	}
	
	public void close(){
		Solenoid piston = (Solenoid)getSolenoids().get(solenoid1);
		piston.set(false);

}
	
	public boolean isRetracted(){
		return ((MDDigitalInput)(getSensors().get(SwitchPosition.retracted.toString()))).get();
}
	
	public boolean isExtended(){
		return ((MDDigitalInput)(getSensors().get(SwitchPosition.extended.toString()))).get();
		
}

	
	
	
}


# Quadcopter-RaspberryPi

**This readme will be updated soon!**

## Goals
My goal is to build and program a quadcopter from scratch. It will feature:
  * a custom remote control
  * an Android App for controlling and displaying advanced options
  * an FPV system (First-person view, a live camera feed) (I will not code anything for this, but it will be included in the hardware overview)
  * and last but not least the quadcopter itself with an Adruino and a Raspberry Pi working together

## Project structure
I am currently working on a DIY quadcopter, which means building and programming it myself as far as possible. The hardware consists of multiple seperated systems:

### 1. The Quadcopter itself:

  The quadcopter consists of two main controllers, that are connected with a serial link over USB:
  1. Raspberry Pi:
  
    That's where the magic happens and what this repository is about. The Raspberry takes all measurements and serial inputs from the Arduino, calculates rotation and translation and sends signals back to the Arduino which are used to control the four motors and two servos from the camera gimbal.
    
  2. Arduino _(Coming soon)_:

    _This part will be put into another repository, which I have not created yet._
  
    The Arduino is used as a bridge between the Raspberry and sensors, motors and serial connections (like the communication to ground control).

### 2. A Remote Control _(Coming soon)_:

_This part will be put into another repository, which I have not created yet._

### 3. An Android App _(Coming soon)_:

_This part will be put into another repository, which I have not created yet._

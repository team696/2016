/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package control;

/**
 *
 * @author CMR
 */
public class PID {
    private double  kP;
    private double  kI;
    private double  kD;
    private double  alpha;
    
    private double  kP1;
    private double  kI1;
    private double  kD1;
    
    private double  P = 0;
    private double  I = 0;
    private double  D = 0;
    
    private double  oldError    = 0;
    private double  cumError    = 0;
    
    private double  time        = System.currentTimeMillis();
    private double  oldTime     = time;
    
    public PID(double _kP, double _kI, double _kD, double _alpha){
        kP      = _kP;
        kI      = _kI;
        kD      = _kD;
        alpha   = _alpha;
        
        kP1     = kP;
        kI1     = kI;
        kD1     = kD;
        
        
    }
    
    public void setPID1(double _kP, double _kI, double _kD){
        kP1 = _kP;
        kI1 = _kI;
        kD1 = _kD;
    }
    
    public double runPID(double error){
        if(signOf(error)>0){
            P(kP,error);
            I(kI,error);
            D(kD,error);
        } else {
            P(kP1,error);
            I(kI1,error);
            D(kD1,error);
        }
        return deadZone(P+I+D,0.01);
    }
    
    private void P(double _kP, double error) {
        P = kP*error;
    }
    
    private void I(double _kI, double error){
        if(signOf(oldError) != signOf(error))cumError = 0;
        cumError+=((1-alpha)*cumError)+(alpha*error*(time-oldTime));
        double returnVal = kI*cumError;
        I = returnVal;
    }
    
    private void D(double _kD, double error){
        double returnVal = kD*((error-oldError)/(time - oldTime));
        oldError = error;
        D = returnVal;
    }
    
    public double getP(){
        return P;
    }
    
    public double getI(){
        return I;
    }
    
    public double getD(){
        return D;
    }
    
    private double deadZone(double val, double lowHighVal){
        if(Math.abs(val)<lowHighVal)return 0;
        return val;
    }
    
    private double signOf(double val){
        if(val>0)return 1;
        return -1;
    }
}

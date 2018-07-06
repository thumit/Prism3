package prism_project.solve;

//import ilog.concert.IloException;
//import ilog.concert.IloLPMatrix;
//import ilog.concert.IloNumVar;
//import ilog.concert.IloNumVarType;
//import ilog.cplex.IloCplex;
//import ilog.cplex.IloCplex.Status;
//import prism_convenience_class.FilesHandle;
//import prism_convenience_class.LibraryHandle;

public class Cplex_Wrapper_Not_Use {
//	private IloCplex cplex;
//	private IloLPMatrix lp;
//	private IloNumVar[] var;
//	
//	private int nvars; 
//	private double[] vlb; 
//	private double[] vub; 
//	private IloNumVarType[] vtype; 
//	private String[] vname; 
//	private double[] objvals; 
//	private int solvingTimeLimit;
//	
//	
//	public Cplex_Wrapper(int nvars, double[] vlb, double[] vub, String[] vname, double[] objvals, int solvingTimeLimit) {
//		this.nvars = nvars;
//		this.vlb = vlb;
//		this.vub = vub;
//		this.vname = vname;
//		this.objvals = objvals;
//		this.solvingTimeLimit = solvingTimeLimit;
//		this.vtype = new IloNumVarType[nvars];
//		for (int i = 0; i < nvars; i++) {
//			vtype[i] = IloNumVarType.Float;
//		}
//		
//		
//		// Add the CPLEX native library path dynamically at run time
//		try {
//			LibraryHandle.setLibraryPath(FilesHandle.get_temporaryFolder().getAbsolutePath().toString());
//			LibraryHandle.addLibraryPath(FilesHandle.get_temporaryFolder().getAbsolutePath().toString());
//			System.out.println("Successfully added .dll files from " + FilesHandle.get_temporaryFolder().getAbsolutePath().toString());	
//		} catch (Exception e) {
//			System.err.println("cplex error - " + e.getClass().getName() + ": " + e.getMessage());
//		}
//		
//		try {
//			cplex = new IloCplex();
//		} catch (IloException e) {
//			System.err.println("cplex error - " + e.getClass().getName() + ": " + e.getMessage());
//		}
//		
//		try {
//			lp = cplex.addLPMatrix();
//		} catch (IloException e) {
//			System.err.println("cplex error - " + e.getClass().getName() + ": " + e.getMessage());
//		}
//		
//		try {
//			var = cplex.numVarArray(cplex.columnArray(lp, nvars), vlb, vub, vtype, vname);
//		} catch (IloException e) {
//			System.err.println("cplex error - " + e.getClass().getName() + ": " + e.getMessage());
//		}
//	}
//	
//	
//	public void addRows(double[] arg0, double[] arg1, int[][] arg2, double[][] arg3) {
//		try {
//			lp.addRows(arg0, arg1, arg2, arg3);
//		} catch (IloException e) {
//			System.err.println("cplex error - " + e.getClass().getName() + ": " + e.getMessage());
//		}
//	}
//	
//	
//	public void setup() {
//		try {
//			// Set objective function to minimize
//			cplex.addMinimize(cplex.scalProd(var, objvals));
//		} catch (IloException e) {
//			System.err.println("cplex error - " + e.getClass().getName() + ": " + e.getMessage());
//		} 
//		
//		
//		try {
//			// Auto choose optimization method
//			cplex.setParam(IloCplex.Param.RootAlgorithm, IloCplex.Algorithm.Auto);
//		} catch (IloException e) {
//			System.err.println("cplex error - " + e.getClass().getName() + ": " + e.getMessage());
//		} 
//		
////		cplex.setParam(IloCplex.DoubleParam.EpGap, 0.00); // Gap is 0%
//		
//		
//		try {
//			 // Set Time limit
//			cplex.setParam(IloCplex.DoubleParam.TimeLimit, solvingTimeLimit);
//		} catch (IloException e) {
//			System.err.println("cplex error - " + e.getClass().getName() + ": " + e.getMessage());
//		}
//		
//		
////		cplex.setParam(IloCplex.BooleanParam.PreInd, false);	// page 40: sets the Boolean parameter PreInd to false, instructing CPLEX not to apply presolve before solving the problem.
//		
////		// turn off presolve to prevent it from completely solving the model before entering the actual LP optimizer (same as above ???)
////		cplex.setParam(IloCplex.Param.Preprocessing.Presolve, false);
//         
//				
////		cplex.setParam(IloCplex.Param.Emphasis.Numerical, true);		// page 94: https://www.ibm.com/support/knowledgecenter/SSSA5P_12.6.3/ilog.odms.studio.help/pdf/paramcplex.pdf
////																		// true --> Exercise extreme caution in computation
////																		// false --> Do not emphasize numerical precision; default
//		
//		
////		numericcplex.setParam(IloCplex.Param.Read.Scale, 0);		// -1 no scaling	0 Equilibration scaling; default	1 More aggressive scaling (page 132)
//		
//		
////		cplex.setParam(IloCplex.DoubleParam.EpMrk, 0.99999);	// Markowitz tolerance
////																// page 152, 154: https://www.ibm.com/support/knowledgecenter/SSSA5P_12.7.0/ilog.odms.studio.help/pdf/usrcplex.pdf
////																// https://www.ibm.com/support/knowledgecenter/en/SS9UKU_12.5.0/com.ibm.cplex.zos.help/UsrMan/topics/cont_optim/simplex/20_num_difficulty.html
//	}
//
//	
//	public int getNcols() {
//		return cplex.getNcols();
//	}
//	
//	
//	public int getNrows() {
//		return cplex.getNrows();
//	}
//	
//	
//	public void exportModel(String arg0) {
//		try {
//			cplex.exportModel(arg0);
//		} catch (IloException e) {
//			System.err.println("cplex error - " + e.getClass().getName() + ": " + e.getMessage());
//		}
//	}
//	
//	
//	public boolean solve() {
//		try {
//			return cplex.solve();
//		} catch (IloException e) {
//			System.err.println("cplex error - " + e.getClass().getName() + ": " + e.getMessage());
//		}
//		return false;
//	}
//	
//	
//	public void writeSolution(String arg0) {
//		try {
//			cplex.writeSolution(arg0);
//		} catch (IloException e) {
//			System.err.println("cplex error - " + e.getClass().getName() + ": " + e.getMessage());
//		}
//	}
//	
//	public double[] getValues() {
//		try {
//			return cplex.getValues(lp);
//		} catch (IloException e) {
//			System.err.println("cplex error - " + e.getClass().getName() + ": " + e.getMessage());
//		}
//		return null;
//	}
//	
//	
//	public double[] getReducedCosts() {
//		try {
//			return cplex.getReducedCosts(lp);
//		} catch (IloException e) {
//			System.err.println("cplex error - " + e.getClass().getName() + ": " + e.getMessage());
//		}
//		return null;
//	}
//	
//	
//	public double[] getDuals() {
//		try {
//			return cplex.getDuals(lp);
//		} catch (IloException e) {
//			System.err.println("cplex error - " + e.getClass().getName() + ": " + e.getMessage());
//		}
//		return null;
//	}
//	
//	
//	public double[] getSlacks() {
//		try {
//			return cplex.getSlacks(lp);
//		} catch (IloException e) {
//			System.err.println("cplex error - " + e.getClass().getName() + ": " + e.getMessage());
//		}
//		return null;
//	}
//
//	
//	public String getStatus() {
//		try {
//			return cplex.getStatus().toString();
//		} catch (IloException e) {
//			System.err.println("cplex error - " + e.getClass().getName() + ": " + e.getMessage());
//		}
//		return null;
//	}
//	
//	
//	public int getAlgorithm() {
//		return cplex.getAlgorithm();
//	}
//	
//	
//	public long getNiterations64() {
//		return cplex.getNiterations64();
//	}
//	
//	
//	public double getObjValue() {
//		try {
//			return cplex.getObjValue();
//		} catch (IloException e) {
//			System.err.println("cplex error - " + e.getClass().getName() + ": " + e.getMessage());
//		}
//		return -9999;
//	}
//	
//	
//	public void end_the_run() {
//		try {
//			cplex.endModel();
//		} catch (IloException e) {
//			System.err.println("cplex error - " + e.getClass().getName() + ": " + e.getMessage());
//		}
//		cplex.end();
//		vlb = null; vub = null; vtype = null; vname = null;	// Clear arrays to save memory
//	}
//	
//	
}

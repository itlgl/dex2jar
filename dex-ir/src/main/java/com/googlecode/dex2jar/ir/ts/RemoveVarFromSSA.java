package com.googlecode.dex2jar.ir.ts;


import com.googlecode.dex2jar.ir.IrMethod;
import com.googlecode.dex2jar.ir.expr.Local;
import com.googlecode.dex2jar.ir.expr.Value;
import com.googlecode.dex2jar.ir.stmt.AssignStmt;
import com.googlecode.dex2jar.ir.stmt.LabelStmt;
import com.googlecode.dex2jar.ir.stmt.Stmt;
import com.googlecode.dex2jar.ir.stmt.VarStartStmt;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public class RemoveVarFromSSA extends StatedTransformer {

    static class V {
        VarStartStmt v;
        Boolean isVar;
        Set<VarStartStmt> children;
    }

    @Override
    public boolean transformReportChanged(IrMethod method) {
        int[] reads = Cfg.countLocalReads(method);
        for (Stmt stmt : method.stmts) {
            if (stmt.st == Stmt.ST.VAR_START) {
                Local left = ((VarStartStmt) stmt).getLeft();
                if (reads[left._ls_index] < 1) {
                    method.stmts.replace(stmt, ((VarStartStmt) stmt).toAssign());
                }
            }
        }
/*
        Map<Local, V> map = new HashMap<>();
        for (Stmt stmt : method.stmts) {
            if (stmt.st == Stmt.ST.IDENTITY) {
                asNotVar(map, (Local) stmt.getOp1());
            } else if (stmt.st == Stmt.ST.VAR_START) {
                asVar(map, (Local) stmt.getOp1());
                if (stmt.getOp2().vt == Value.VT.LOCAL) {
                    link((Local) stmt.getOp1(), (Local) stmt.getOp2());
                }
            } else if (stmt.st == Stmt.ST.ASSIGN) {
                if (stmt.getOp1().vt == Value.VT.LOCAL && stmt.getOp2().vt == Value.VT.LOCAL) {
                    link((Local) stmt.getOp1(), (Local) stmt.getOp2());
                }
            }
        }
        if (method.phiLabels != null) {
            for (LabelStmt phiLabel : method.phiLabels) {
                if (phiLabel.phis != null) {
                    for (AssignStmt phi : phiLabel.phis) {
                        for (Value op : phi.getOp2().getOps()) {
                            link((Local) phi.getOp1(), (Local) op);
                        }
                    }
                }
            }
        }
        */
        return false;
    }

    private void link(Local c, Local p) {

    }

    private void asVar(Map<Local, V> map, Local op1) {

    }

    private void asNotVar(Map<Local, V> map, Local op1) {

    }
}

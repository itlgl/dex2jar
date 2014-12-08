package com.googlecode.dex2jar.ir.stmt;


import com.googlecode.dex2jar.ir.LabelAndLocalMapper;
import com.googlecode.dex2jar.ir.expr.Local;
import com.googlecode.dex2jar.ir.expr.Value;

public class VarStartStmt extends AssignStmt {
    public VarStartStmt(Local left, Value op2, String name, String type, String signature) {
        super(ST.VAR_START, left, op2);
        this.name = name;
        this.type = type;
        this.signature = signature;
    }

    public AssignStmt toAssign() {
        return new AssignStmt(ST.ASSIGN, op1, op2);
    }

    public Local getLeft() {
        return (Local) super.getOp1();
    }

    @Override
    public void setOp1(Value op1) {
        if (op1.vt != Value.VT.LOCAL) {
            throw new IllegalArgumentException();
        }
        super.setOp1(op1);
    }

    public String name;
    public String type;
    public String signature;

    @Override
    public Stmt clone(LabelAndLocalMapper mapper) {
        return new VarStartStmt((Local) op1.clone(mapper), op2.clone(mapper), name, type, signature);
    }

    @Override
    public String toString() {
        return op1.toString() + " [" + name + ":" + type +
                "]=" + op2.toString();
    }
}

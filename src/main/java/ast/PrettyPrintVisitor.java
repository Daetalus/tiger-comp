package ast;

import ast.Ast.Class.ClassSingle;
import ast.Ast.Dec;
import ast.Ast.Exp;
import ast.Ast.Exp.Add;
import ast.Ast.Exp.And;
import ast.Ast.Exp.ArraySelect;
import ast.Ast.Exp.Call;
import ast.Ast.Exp.False;
import ast.Ast.Exp.Id;
import ast.Ast.Exp.Length;
import ast.Ast.Exp.Lt;
import ast.Ast.Exp.NewIntArray;
import ast.Ast.Exp.NewObject;
import ast.Ast.Exp.Not;
import ast.Ast.Exp.Num;
import ast.Ast.Exp.Sub;
import ast.Ast.Exp.This;
import ast.Ast.Exp.Times;
import ast.Ast.Exp.True;
import ast.Ast.MainClass;
import ast.Ast.Method;
import ast.Ast.Method.MethodSingle;
import ast.Ast.Program;
import ast.Ast.Stm;
import ast.Ast.Stm.Assign;
import ast.Ast.Stm.AssignArray;
import ast.Ast.Stm.Block;
import ast.Ast.Stm.If;
import ast.Ast.Stm.Print;
import ast.Ast.Stm.While;
import ast.Ast.Type.Boolean;
import ast.Ast.Type.ClassType;
import ast.Ast.Type.Int;
import ast.Ast.Type.IntArray;

public class PrettyPrintVisitor implements Visitor {
  private StringBuilder sb;

  private int indentLevel;

  public PrettyPrintVisitor() {
    this.sb = new StringBuilder();
    this.indentLevel = 4;
  }

  private void indent() {
    this.indentLevel += 2;
  }

  private void unIndent() {
    this.indentLevel -= 2;
  }

  private void printSpaces() {
    int i = this.indentLevel;
    while (i-- != 0)
      this.say(" ");
  }

  private void sayln(String s) {
    sb.append(s);
    sb.append("\n");
  }

  private void say(String s) {
    sb.append(s);
  }

  // /////////////////////////////////////////////////////
  // expressions
  @Override
  public void visit(Add e) {
    this.say("(");
    e.left.accept(this);
    this.say(" + ");
    e.right.accept(this);
    this.say(")");
  }

  @Override
  public void visit(And e) {
    this.say("(");
    e.left.accept(this);
    this.say(" && ");
    e.right.accept(this);
    this.say(")");
  }

  @Override
  public void visit(ArraySelect e) {
    this.say("(");
    e.array.accept(this);
    this.say("[");
    e.index.accept(this);
    this.say("]");
    this.say(")");
  }

  @Override
  public void visit(Call e) {
    this.say("(");
    e.caller.accept(this);
    this.say("." + e.id + "(");
    int i = 0;
    for (Exp.T x : e.args) {
      i++;
      x.accept(this);
      if (i != e.args.size()) {
        this.say(", ");
      } else {
      }
    }
    this.say(")");
    this.say(")");
  }

  @Override
  public void visit(False e) {
    this.say("false");
  }

  @Override
  public void visit(Id e) {
    this.say(e.id);
  }

  @Override
  public void visit(Length e) {
    this.say("(");
    e.array.accept(this);
    this.say(".length");
    this.say(")");
  }

  @Override
  public void visit(Lt e) {
    this.say("(");
    e.left.accept(this);
    this.say(" < ");
    e.right.accept(this);
    this.say(")");
  }

  @Override
  public void visit(NewIntArray e) {
    this.say("(");
    this.say("new int[");
    e.exp.accept(this);
    this.say("]");
    this.say(")");
  }

  @Override
  public void visit(NewObject e) {
    this.say("(");
    this.say("new " + e.id + "()");
    this.say(")");
  }

  @Override
  public void visit(Not e) {
    this.say("(");
    this.say("!(");
    e.exp.accept(this);
    this.say(")");
    this.say(")");
  }

  @Override
  public void visit(Num e) {
    this.say(String.valueOf(e.num));
  }

  @Override
  public void visit(Sub e) {
    this.say("(");
    e.left.accept(this);
    this.say(" - ");
    e.right.accept(this);
    this.say(")");
  }

  @Override
  public void visit(This e) {
    this.say("this");
  }

  @Override
  public void visit(Times e) {
    this.say("(");
    e.left.accept(this);
    this.say(" * ");
    e.right.accept(this);
    this.say(")");
  }

  @Override
  public void visit(True e) {
    this.say("true");
  }

  // statements
  @Override
  public void visit(Assign s) {
    this.printSpaces();
    this.say(s.id + " = ");
    s.exp.accept(this);
    this.say(";\n");
  }

  @Override
  public void visit(AssignArray s) {
    this.printSpaces();
    this.say(s.id + "[");
    s.index.accept(this);
    this.say("]");
    this.say(" = ");
    s.exp.accept(this);
    this.say(";\n");
  }

  @Override
  public void visit(Block s) {
    this.sayln("");
    this.printSpaces();
    this.sayln("{");
    this.indent();
    for (Stm.T b : s.stms)
      b.accept(this);
    this.unIndent();
    this.printSpaces();
    this.sayln("}");
  }

  @Override
  public void visit(If s) {
    this.printSpaces();
    this.say("if (");
    s.condition.accept(this);
    this.sayln(")");
    this.printSpaces();
    s.thenn.accept(this);
    this.printSpaces();
    this.sayln("else");
    s.elsee.accept(this);
  }

  @Override
  public void visit(Print s) {
    this.printSpaces();
    this.say("System.out.println (");
    s.exp.accept(this);
    this.sayln(");");
  }

  @Override
  public void visit(While s) {
    this.printSpaces();
    this.say("while (");
    s.condition.accept(this);
    this.say(")");
    this.indent();
    s.body.accept(this);
    this.unIndent();
    this.printSpaces();
  }

  // type
  @Override
  public void visit(Boolean t) {
    this.say("Boolean");
  }

  @Override
  public void visit(ClassType t) {
    this.say(t.id);
  }

  @Override
  public void visit(Int t) {
    this.say("int");
  }

  @Override
  public void visit(IntArray t) {
    this.say("int[]");
  }

  // dec
  @Override
  public void visit(Dec.DecSingle d) {
    d.type.accept(this);
    this.say(" " + d.id);
  }

  // method
  @Override
  public void visit(MethodSingle m) {
    this.say("  public ");
    m.retType.accept(this);
    this.say(" " + m.id + "(");
    int i = 0;
    for (Dec.T d : m.formals) {
      Dec.DecSingle dec = (Dec.DecSingle) d;
      i++;
      dec.accept(this);
      if (i != m.formals.size()) {
        this.say(", ");
      }
    }
    this.sayln(")");
    this.sayln("  {");
    for (Dec.T d : m.locals) {
      Dec.DecSingle dec = (Dec.DecSingle) d;
      this.say("    ");
      dec.accept(this);
      this.say(";\n");
    }
    this.sayln("");
    for (Stm.T s : m.stms)
      s.accept(this);
    this.say("    return ");
    m.retExp.accept(this);
    this.sayln(";");
    this.sayln("  }");
  }

  // class
  @Override
  public void visit(ClassSingle c) {
    this.say("class " + c.id);
    if (c.extendss != null) {
      this.sayln(" extends " + c.extendss);
    } else {
      this.sayln("");
    }
    this.sayln("{");
    for (Dec.T d : c.decs) {
      Dec.DecSingle dec = (Dec.DecSingle) d;
      this.say("  ");
      dec.type.accept(this);
      this.say(" ");
      this.sayln(dec.id + ";");
    }
    for (Method.T mthd : c.methods)
      mthd.accept(this);
    this.sayln("}");
  }

  // main class
  @Override
  public void visit(MainClass.MainClassSingle c) {
    this.sayln("class " + c.id);
    this.sayln("{");
    this.sayln("  public static void main (String[] " + c.arg + ")");
    this.sayln("  {");
    c.stm.accept(this);
    this.sayln("  }");
    this.sayln("}");
  }

  // program
  @Override
  public void visit(Program.ProgramSingle p) {
    p.mainClass.accept(this);
    this.sayln("");
    for (ast.Ast.Class.T classs : p.classes) {
      classs.accept(this);
    }
    this.say("\n\n");
  }

  public String toString() {
    return this.sb.toString();
  }
}

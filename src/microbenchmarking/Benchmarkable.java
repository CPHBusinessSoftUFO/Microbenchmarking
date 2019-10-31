/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package microbenchmarking;

import java.util.function.IntToDoubleFunction;

public abstract class Benchmarkable implements IntToDoubleFunction {
  public void setup() { }
  @Override
  public abstract double applyAsDouble(int i);
}

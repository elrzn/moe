package org.moe.interpreter

import org.scalatest.FunSuite
import org.scalatest.BeforeAndAfter

import org.moe.runtime._
import org.moe.ast._

class UnlessNodeTestSuite extends FunSuite with BeforeAndAfter with InterpreterTestUtils {

  test("... basic test with Unless") {
    val ast = wrapSimpleAST(
      List(
        UnlessNode(
          BooleanLiteralNode( false ),
          IntLiteralNode( 42 )
        )
      )
    )
    val result = Interpreter.eval( MoeRuntime.getRootEnv, ast )
    assert( result.asInstanceOf[ MoeIntObject ].getNativeValue === 42 )
  }

  test("... basic (true) test with Unless") {
    val ast = wrapSimpleAST(
      List(
        UnlessNode(
          BooleanLiteralNode( true ),
          IntLiteralNode( 42 )
        )
      )
    )
    val result = Interpreter.eval( MoeRuntime.getRootEnv, ast )
    assert( result === MoeRuntime.NativeObjects.getUndef )
  }

  test("... basic test with UnlessElse") {
    val ast = wrapSimpleAST(
      List(
        UnlessElseNode(
          BooleanLiteralNode( false ),
          IntLiteralNode( 42 ),
          IntLiteralNode( 21 )
        )
      )
    )
    val result = Interpreter.eval( MoeRuntime.getRootEnv, ast )
    assert( result.asInstanceOf[ MoeIntObject ].getNativeValue === 42 )
  }

  test("... basic (true) test with UnlessElse") {
    val ast = wrapSimpleAST(
      List(
        UnlessElseNode(
          BooleanLiteralNode( true ),
          IntLiteralNode( 42 ),
          IntLiteralNode( 21 )
        )
      )
    )
    val result = Interpreter.eval( MoeRuntime.getRootEnv, ast )
    assert( result.asInstanceOf[ MoeIntObject ].getNativeValue === 21 )
  }

}
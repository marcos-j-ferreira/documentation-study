/**
 *  Arquivo main
 * 
 *  - inicia a classe calculadora
 *  - passa parametro para as operações 
 *  - exibi o resultado das quatro operações de forma organizada.
 */

package src;

public class Main{

    public static void main(String[] args){

        Calculadora cal1 = new Calculadora();

        double resultSoma = cal1.somar(10, 10);
        double resultMult = cal1.multiplicar(8,10);
        double ressultSub =  cal1.subtrair(10,5);
        double ressultDiv = cal1.dividir(20,4);

        System.out.println(" === Resultados das operações basicas ===\n\n Soma de 10 + 10 = "+resultSoma+"\n multiplicação 8 X 10 = "+resultMult+"\n subtração 5 - 10 = "+ ressultSub+ "\n Divisão 20 / 4 = "+ ressultDiv);

    }
}
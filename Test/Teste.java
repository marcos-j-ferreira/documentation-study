/**
 *  Teste das funções da calculadora
 * 
 * - Realiza uma pequenos teste na função de somar.
 * 
 * @param {number} - Usa uma array com números definidos.
 * @return - Após a soma dos números, eles são comparados.   
 *  
 *  @author Marcos J. Lemes
 *  @version 1.0
 */

import src.Calculadora;

public class Teste{

    private static Calculadora cal = new Calculadora();

    public static final void testSomar(){

        int[] number = {1,2,56,23,78,1,12,12,3,43,6,8,2,2,23};
        int size = number.length - 1;
        int teste = 0;

        for(int i = 0; i < number.length; i++){
            double result = cal.somar(number[i], number[size - i]);
            double soma = number[i] + number[size - i];

            if(result == soma){
                teste++;
                System.out.println("\nTeste passou\n número 1: "+number[i]+" número 2: "+number[size - i]+"\n Resultado: "+ soma);
            }else{
                System.out.println("\nTeste falhou!!! \n");
            }
        }
        int resultTest = teste - size;
        System.out.println("\n --- Passaram "+teste+" Falharam: "+ resultTest+" ---");
    }

    public static void main(String[] args){
        testSomar();
    }
}
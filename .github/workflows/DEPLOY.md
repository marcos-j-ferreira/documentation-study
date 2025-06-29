### **🔒 CI/CD e Segurança no Deploy**  

#### **1. Merge na `main` só após testes passarem**  
- **Como funciona**:  
  - O GitHub Actions roda testes automatizados em todo **push** ou **pull request**.  
  - A branch `main` está protegida: **nenhum código é mergeado** sem que os testes tenham sucesso.  

- **Como configurar**:  
  ```yml
  # .github/workflows/test.yml  
  on: [push, pull_request]  
  jobs:  
    test:  
      runs-on: ubuntu-latest  
      steps:  
        - uses: actions/checkout@v4  
        - run: npm test  # ou seus comandos de teste  
  ```  
  - Ative em: **Settings > Branches > Branch protection rules**.  

---

#### **2. Dados sensíveis (AWS, APIs, etc.)**  
- **Nunca armazene no repositório**:  
  - Chaves da AWS, tokens de API, senhas **nunca** devem estar em arquivos (`.env`, `.yml`, etc.).  

- **Use GitHub Secrets**:  
  - Adicione credenciais em: **Settings > Secrets and variables > Actions**.  
  - Acesse-as no workflow:  
    ```yml
    env:  
      AWS_ACCESS_KEY: ${{ secrets.AWS_ACCESS_KEY }}  
    ```  

- **Método mais seguro (OIDC)**:  
  - Substitua chaves estáticas por autenticação temporária com AWS IAM.  
  - Exemplo:  
    ```yml
    - name: Configure AWS Credentials  
      uses: aws-actions/configure-aws-credentials@v4  
      with:  
        role-to-assume: arn:aws:iam::123456789012:role/github-role  
    ```  

---

### **📌 Exemplo de Workflow Completo**  
```yml
name: Deploy  
on:  
  push:  
    branches: [main]  

jobs:  
  test:  
    runs-on: ubuntu-latest  
    steps:  
      - uses: actions/checkout@v4  
      - run: npm test  

  deploy:  
    needs: test  # Só executa se os testes passarem  
    runs-on: ubuntu-latest  
    steps:  
      - uses: actions/checkout@v4  
      - name: Configure AWS  
        uses: aws-actions/configure-aws-credentials@v4  
        with:  
          role-to-assume: ${{ secrets.AWS_ROLE }}  
      - run: ./deploy.sh  
```  

---

### **✅ Boas Práticas**  
1. **Proteja a `main`**: Ative "Require status checks to pass" nas regras da branch.  
2. **Revise credenciais**: Rotacione chaves periodicamente e use OIDC quando possível.  
3. **Documente**: Adicione um link para este README no seu workflow.  

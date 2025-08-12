# 🚀 SimpleERP – Sistema de Gestão Empresarial com Spring Boot

> Sistema ERP moderno para emissão de **NF-e, NFC-e, NFS-e**, boletos, vendas e controle financeiro.  
> Desenvolvido com **Spring Boot, Java, MySQL e JWT**, com foco em boas práticas de Engenharia de Software.

![Spring Boot](https://img.shields.io/badge/Spring_Boot-2DF443?style=for-the-badge&logo=spring&logoColor=white)
![Java](https://img.shields.io/badge/java-%23ED8B00.svg?style=for-the-badge&logo=java&logoColor=white)
![MySQL](https://img.shields.io/badge/MySQL-00000F?style=for-the-badge&logo=mysql&logoColor=white)
![Swagger](https://img.shields.io/badge/Swagger-28A745?style=for-the-badge&logo=swagger&logoColor=white)

---

## 🌟 Sobre o Projeto

Este sistema é uma versão moderna e escalável do meu projeto real:  
👉 [**CapitalERP.com.br**](https://capitalerp.com.br)

Funcionalidades inspiradas em um ERP **em produção**, usado por empresas para:

- ✅ Emissão de **NF-e, NFC-e e NFS-e**
- ✅ Geração de **boletos bancários**
- ✅ Gestão de **vendas e frente de caixa**
- ✅ Controle de **ordens de serviço**
- ✅ Cadastros de produtos, clientes e fornecedores
- ✅ Controle financeiro com gráficos

---

## 💡 Tecnologias Utilizadas

- **Backend**: Spring Boot (Java 17)
- **Segurança**: JWT + Spring Security
- **Banco de Dados**: MySQL
- **Documentação**: Swagger UI (OpenAPI)
- **Padrões**: DTO, Camadas (Controller, Service, Repository), Lombok
- **Ferramentas de IA**: GitHub Copilot e ChatGPT usados para aceleração do desenvolvimento, refatoração e documentação.

---

## 📸 Telas do Sistema (Capturas Reais)

### 🔹 Login e Dashboard
![Login](images/Captura%20de%20tela%20de%202025-08-12%2018-11-25.png)

### 🔹 Gestão de Produtos
![Produtos](images/Captura%20de%20tela%20de%202025-08-12%2018-11-55.png)

### 🔹 Emissão de Nota Fiscal
![Nota Fiscal](images/Captura%20de%20tela%20de%202025-08-12%2018-12-17.png)

### 🔹 Controle Financeiro
![Contas a Pagar e Receber](images/Captura%20de%20tela%20de%202025-08-12%2018-12-50.png)

---

## 🛠️ Funcionalidades do CapitalERP (em Produção)

### 📄 [Emissão de documentos fiscais](https://capitalerp.com.br)
![](https://i0.wp.com/capitalerp.com.br/wp-content/uploads/2023/03/file.png?w=512&ssl=1)
> Emita NF-e, NFC-e e NFS-e com poucos cliques, envie por e-mail ou WhatsApp.

### 💵 [Geração de boletos](https://capitalerp.com.br)
![](https://i0.wp.com/capitalerp.com.br/wp-content/uploads/2023/03/barcode.png?w=512&ssl=1)
> Gere boletos avulsos ou vinculados a vendas.

### 🛒 [Vendas e frente de caixa](https://capitalerp.com.br)
![](https://i0.wp.com/capitalerp.com.br/wp-content/uploads/2023/03/acquisition.png?w=512&ssl=1)
> Sistema rápido para emissão de cupom fiscal.

### 🔧 [Ordem de serviço](https://capitalerp.com.br)
![](https://i0.wp.com/capitalerp.com.br/wp-content/uploads/2023/03/customer-service.png?w=512&ssl=1)
> Controle de reparos, garantias e serviços.

### 💼 [Cadastros completos](https://capitalerp.com.br)
![](https://i0.wp.com/capitalerp.com.br/wp-content/uploads/2023/03/group.png?w=512&ssl=1)
> Produtos, serviços, clientes e fornecedores.

### 📊 [Gestão financeira](https://capitalerp.com.br)
![](https://i0.wp.com/capitalerp.com.br/wp-content/uploads/2023/03/hand.png?w=512&ssl=1)
> Controle de contas a pagar e receber com gráficos.

---

## 🔐 Como Fazer Login

O sistema usa **autenticação com JWT**. Faça login via API:

```bash
POST /api/auth/login
Content-Type: application/json

{
  "email": "marlon@capitalerp.com.br",
  "senha": "123456"
}
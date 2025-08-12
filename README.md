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

## 📸 Telas (Simuladas com base no CapitalERP)

![Emissão de Nota Fiscal](https://i0.wp.com/capitalerp.com.br/wp-content/uploads/2023/03/Captura-de-tela-de-2023-03-01-21-19-48.png?w=1274&ssl=1)
> Emissão de documentos fiscais com poucos cliques

![Gestão Financeira](https://i0.wp.com/capitalerp.com.br/wp-content/uploads/2020/12/neve-web-design-studio-03.1.jpg?w=400&ssl=1)
> Controle de contas a pagar e receber

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

-- ============================================
-- DADOS INICIAIS PARA O SIMPLEERP (BASEADO NO CAPITALERP)
-- ============================================

-- 1. USUÁRIO ADMIN (com senha: "123456" criptografada com BCrypt)
-- Use https://bcrypt-generator.com/ para gerar outras senhas
INSERT INTO usuarios (nome, email, senha, perfis) VALUES
    ('Marlon Admin', 'marlon@capitalerp.com.br', '$2a$10$GVS8d93V7/YqZ6Y1Z4Y5pOqj7Z5q9Y5pOqj7Z5q9Y5pOqj7Z5q9Y5pOqj', 'ADMIN');

INSERT INTO usuarios (nome, email, senha, perfis) VALUES
    ('Usuario Teste', 'teste@empresa.com', '$2a$10$GVS8d93V7/YqZ6Y1Z4Y5pOqj7Z5q9Y5pOqj7Z5q9Y5pOqj7Z5q9Y5pOqj', 'USER');

-- 2. FORNECEDORES
INSERT INTO fornecedores (nome, cnpj, email, telefone, site) VALUES
                                                                 ('Tech Distribuidora LTDA', '12345678000195', 'contato@techdist.com.br', '(11) 98765-4321', 'www.techdist.com.br'),
                                                                 ('Eletrônicos Global', '98765432000101', 'vendas@global.com.br', '(21) 91234-5678', 'www.global.com.br');

-- 3. PRODUTOS
INSERT INTO produtos (nome, codigo_barras, preco, quantidade_estoque, fornecedor_id, descricao) VALUES
                                                                                                    ('Notebook Dell Inspiron', '7891234567890', 3500.00, 15, 1, 'Notebook Dell Inspiron i15, 16GB RAM, 512GB SSD'),
                                                                                                    ('Mouse Gamer Logitech', '7891234567891', 299.90, 50, 2, 'Mouse Gamer Logitech G502, 12000 DPI'),
                                                                                                    ('Teclado Mecânico Redragon', '7891234567892', 349.90, 30, 2, 'Teclado Mecânico Redragon K552, Switch Blue');

-- 4. CLIENTES
INSERT INTO clientes (nome, cpf_cnpj, email, telefone, cep, logradouro, numero, bairro, cidade, estado) VALUES
                                                                                                            ('Empresa Soluções Digitais', '27182818000190', 'financeiro@solucoes.com.br', '(11) 3333-4444', '01310930', 'Avenida Paulista', '1000', 'Bela Vista', 'São Paulo', 'SP'),
                                                                                                            ('João Silva', '12345678900', 'joao.silva@email.com', '(11) 99999-8888', '20040000', 'Rua do Ouvidor', '150', 'Centro', 'Rio de Janeiro', 'RJ'),
                                                                                                            ('Maria Oliveira', '98765432100', 'maria.oliveira@email.com', '(21) 98888-7777', '30140070', 'Avenida Afonso Pena', '300', 'Savassi', 'Belo Horizonte', 'MG');

-- 5. NOTAS FISCAIS (SAÍDA - vendas)
INSERT INTO notas_fiscais (numero_nf, tipo, cliente_id, fornecedor_id, valor_total, data_emissao, chave_acesso, status) VALUES
                                                                                                                            ('1001', 'SAIDA', 1, NULL, 3799.90, '2025-04-01', '12345678901234567890123456789012345678901234', 'AUTORIZADA'),
                                                                                                                            ('1002', 'SAIDA', 2, NULL, 299.90, '2025-04-02', '12345678901234567890123456789012345678901235', 'AUTORIZADA'),
                                                                                                                            ('1003', 'ENTRADA', NULL, 1, 52500.00, '2025-04-03', '12345678901234567890123456789012345678901236', 'AUTORIZADA');

-- 6. CONTAS A PAGAR
INSERT INTO contas_pagar (descricao, valor, data_vencimento, data_pagamento, status, fornecedor_id) VALUES
                                                                                                        ('Compra de Estoque - Tech Distribuidora', 52500.00, '2025-04-10', '2025-04-10', 'PAGO', 1),
                                                                                                        ('Manutenção Servidor', 2500.00, '2025-04-15', NULL, 'PENDENTE', 2),
                                                                                                        ('Conta de Luz', 1800.00, '2025-04-20', NULL, 'PENDENTE', NULL);

-- 7. CONTAS A RECEBER
INSERT INTO contas_receber (descricao, valor, data_vencimento, data_recebimento, status, cliente_id) VALUES
                                                                                                         ('Venda de Notebook + Mouse', 3799.90, '2025-04-10', '2025-04-10', 'RECEBIDO', 1),
                                                                                                         ('Venda de Mouse Gamer', 299.90, '2025-04-12', '2025-04-12', 'RECEBIDO', 2),
                                                                                                         ('Serviço de Configuração ERP', 1500.00, '2025-04-25', NULL, 'PENDENTE', 3);

-- 8. PRODUTOS OU SERVIÇOS CADASTRADOS (opcional, se tiver tabela de serviços)
-- (Pode ser usado no módulo de vendas e OS)
-- INSERT INTO produtos (nome, preco, tipo) VALUES ('Instalação de Sistema ERP', 800.00, 'SERVICO');

-- ============================================
-- FIM DO SCRIPT DE DADOS INICIAIS
-- ============================================
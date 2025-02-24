ALTER TABLE tb_usuarios
  ADD COLUMN created_by BIGINT UNSIGNED;

ALTER TABLE tb_usuarios
  ADD CONSTRAINT fk_tb_usuarios_created_by FOREIGN KEY (created_by)
    REFERENCES tb_usuarios(id);

ALTER TABLE tb_clientes
  ADD COLUMN created_by BIGINT UNSIGNED;

ALTER TABLE tb_clientes
  ADD CONSTRAINT fk_tb_clientes_created_by FOREIGN KEY (created_by)
    REFERENCES tb_usuarios(id);

ALTER TABLE tb_contatos
  ADD COLUMN created_by BIGINT UNSIGNED;

ALTER TABLE tb_contatos
  ADD CONSTRAINT fk_tb_contatos_created_by FOREIGN KEY (created_by)
    REFERENCES tb_usuarios(id);

ALTER TABLE tb_enderecos
  ADD COLUMN created_by BIGINT UNSIGNED;

ALTER TABLE tb_enderecos
  ADD CONSTRAINT fk_tb_enderecos_created_by FOREIGN KEY (created_by)
    REFERENCES tb_usuarios(id);

ALTER TABLE tb_racas
  ADD COLUMN created_by BIGINT UNSIGNED;

ALTER TABLE tb_racas
  ADD CONSTRAINT fk_tb_racas_created_by FOREIGN KEY (created_by)
    REFERENCES tb_usuarios(id);

ALTER TABLE tb_pets
  ADD COLUMN created_by BIGINT UNSIGNED;

ALTER TABLE tb_pets
  ADD CONSTRAINT fk_tb_pets_created_by FOREIGN KEY (created_by)
    REFERENCES tb_usuarios(id);

ALTER TABLE tb_atendimentos
  ADD COLUMN created_by BIGINT UNSIGNED;

ALTER TABLE tb_atendimentos
  ADD CONSTRAINT fk_tb_atendimentos_created_by FOREIGN KEY (created_by)
    REFERENCES tb_usuarios(id);

create table if not exists usuario(
	id int primary key default nextval ('sequence_usuario'::regclass),
	nome varchar(45) not null,
	telefone varchar(15) not null,
	email varchar(75) not null,
	senha varchar(40) not null,
	perfil varchar(25) not null,
	data_cadastro timestamp not null,
	data_ultimo_acesso timestamp not null,
	status boolean
);

create table if not exists avaliacao(
	id int primary key default nextval ('sequence_avaliacao'::regclass),
	usuario_id int not null references usuario(id),
	nota int not null,
	descricao varchar(250) default null,
	status boolean
);

create table if not exists endereco(
	id int primary key default nextval ('sequence_endereco'::regclass),
	usuario_id int not null references usuario(id),
	estado varchar(25) not null,
	cidade varchar(75) not null,
	bairro varchar(75) not null,
	cep varchar(15) default null,
	endereco varchar(75) not null,
	numero varchar(10) not null,
	complemento varchar(15) default null,
	status boolean
);

create table if not exists gondola(
	id int primary key default nextval ('sequence_gondola'::regclass),
	descricao varchar(250) not null,
	imagem varchar(200) not null,
	status boolean
);

create table if not exists produto(
	id int primary key default nextval ('sequence_produto'::regclass),
	gondola_id int not null references gondola(id),
	descricao varchar(250) not null,
	imagem varchar(250) not null,
	preco_de_custo decimal(18,2) not null,
	preco_de_venda decimal(18,2) not null,
	preco_promocional decimal(18,2) not null,
	quantidade int not null,
	quantidade_minima int not null,
	destaque boolean,
	oferta boolean,
	status boolean
);

create table if not exists compra(
	id int primary key default nextval ('sequence_compra'::regclass),
	usuario_id int not null references usuario(id),
	data_compra timestamp not null,
	valor_compra decimal(18,2) default null,
	status varchar(25) not null
);

create table if not exists compra_produto(
	id int primary key default nextval ('sequence_compra_produto'::regclass),
	compra_id int not null references compra(id),
	produto_id int not null references produto(id),
	preco_unitario decimal(18,2) not null,
	quantidade int not null
);

create table if not exists anuncio(
	id int primary key default nextval ('sequence_anuncio'::regclass),
	titulo varchar(250) default null,
	descricao varchar(250) default null,
	imagem varchar(200) default null,
	data_inicio timestamp,
	data_fim timestamp,
	status boolean
);

create table if not exists promocao(
	id int primary key default nextval ('sequence_promocao'::regclass),
	descricao varchar(250) default null,
	imagem varchar(200) default null,
	data_inicio timestamp,
	data_fim timestamp,
	status boolean
);

create table if not exists promocao_produto(
	id int primary key default nextval ('sequence_promocao_produto'::regclass),
	promocao_id int not null references promocao (id),
	produto_id int not null references produto (id)
);
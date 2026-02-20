-- ============================================
-- 1. TABLA DE MARCAS
-- ============================================
CREATE TABLE marcas (
    id SERIAL PRIMARY KEY,
    nombre VARCHAR(50) NOT NULL UNIQUE
);

-- ============================================
-- 2. TABLA DE ARTÍCULOS
-- ============================================
CREATE TABLE articulos (
    id SERIAL PRIMARY KEY,
    marca_id INTEGER NOT NULL,
    nombre VARCHAR(100) NOT NULL,
    tipo VARCHAR(50) NOT NULL,
    FOREIGN KEY (marca_id) REFERENCES marcas(id) ON DELETE CASCADE
);

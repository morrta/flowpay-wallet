CREATE TABLE wallet (
                        id UUID PRIMARY KEY,
                        balance DECIMAL(19, 2) NOT NULL,
                        created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
                        updated_at TIMEStAMP DEFAULT CURRENT_TIMESTAMP
);
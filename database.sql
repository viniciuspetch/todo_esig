-- Table: public.items

-- DROP TABLE public.items;

CREATE TABLE public.items
(
    id integer NOT NULL DEFAULT nextval('items_id_seq'::regclass),
    content text COLLATE pg_catalog."default",
    checked boolean
)

TABLESPACE pg_default;

ALTER TABLE public.items
    OWNER to postgres;
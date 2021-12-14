package com.informatorio.tpJavaFinal.entity;


public enum TipoUsuario {


    USUARIO,
    COLABORADOR,
    OWNER;

    /* USUARIO(1),
    COLABORADOR(2),
    OWNER(3);

    private TipoUsuario(Integer id) {
        this.id = id;
    }

    private Integer id;

    public static TipoUsuario getType(Integer id){
        if (id == null){
            return null;
        }

        for (TipoUsuario tipo : TipoUsuario.values()){
            if(id.equals(tipo.getId())){
                return tipo;
            }
        }

        throw new IllegalArgumentException("No matching type for id " + id);
    }

    public int getId(){
        return id;
    }
 */

}

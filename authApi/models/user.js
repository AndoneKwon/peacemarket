module.exports = (sequelize, DataTypes)=>(
    sequelize.define('user',{
        email :{
            type : DataTypes.STRING(30),
            allowNull : true,
            unique : true,
        },

        password : {
            type : DataTypes.STRING(200),
            allowNull : false,
        },

        salt : {
            type : DataTypes.STRING(64),
            allowNull : false,
        },


        nickname : {
            type : DataTypes.STRING(40),
            allowNull : false
        },

        phone_num :{
            type : DataTypes.STRING(100)
        },

        accessedAt : {
            type : DataTypes.DATE,
            allowNull : false,
            defaultValue : sequelize.literal('now()'),
        },
    
    },
    
        {
            timestamps : true,
            paranoid : true,
        }

    
    )

);
